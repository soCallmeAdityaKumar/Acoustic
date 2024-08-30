package com.example.acoustic.detail.viemModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acoustic.common.Resource
import com.example.acoustic.common.TYPE
import com.example.acoustic.detail.model.DetailModel
import com.example.acoustic.detail.useCases.AlbumUseCase
import com.example.acoustic.detail.useCases.ArtistAlbumUseCase
import com.example.acoustic.detail.useCases.ArtistUseCase
import com.example.acoustic.detail.useCases.PlayListUseCase
import com.example.acoustic.library.useCases.LibraryPlayListUseCases
import com.example.acoustic.login.domain.data.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class DetailViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val albumUseCases: AlbumUseCase,
    private val artistAlbumUseCase: ArtistAlbumUseCase,
    private val playListUseCases: PlayListUseCase,
    private val artistUseCase: ArtistUseCase
):ViewModel() {
     var token:String=""
     var id:String=""
     var name:String?=null
     var type:String?=null
     var image:String?=null
     var artist:String?=null

    private val _detailState= mutableStateOf(DetailClass())
    val detailState: State<DetailClass> = _detailState

    init {
        id=savedStateHandle.get<String>("id").toString()
        type=savedStateHandle.get<String?>("type").toString()
        Log.d("getAlbum","type->$type, id->$id")
        val sharedPref= SharedPref(context)
        token=sharedPref.value("USER_TOKEN").toString()
        if (token != null) {
            when(type?.uppercase()){
                TYPE.ALBUM.toString()->{
                    getAlbum(token)
                }
                 TYPE.GENRE.toString()->{

                }
                TYPE.CATEGORIES.toString()->{
                    getAlbum(token)
                }
                TYPE.PLAYLIST.toString()->{
                    getPlaylist(token)
                }
                TYPE.ARTIST.toString()->{
                    Log.d("getAlbum","inside the Artist block")
                    getArtist(token)
                    getAlbumOfArtist(token)
                }
            }
        }
    }
    private  fun getAlbum(token:String){
        albumUseCases.invoke(token,id).onEach {result->
            Log.d("getAlbum","result->${result}")
            when(result){
                is Resource.Success ->{
                    _detailState.value= DetailClass(result =result.data)
                    resetValues()
                    Log.d("HomeViewModel","Success->${_detailState.value.result}")
                }
                is Resource.Error->{
                    _detailState.value= DetailClass(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _detailState.value= DetailClass(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private  fun getAlbumOfArtist(token: String){
        artistAlbumUseCase.invoke(token,id).onEach {
                result->
            when(result){
                is Resource.Success ->{
                    _detailState.value= DetailClass(result =result.data)
                    Log.d("HomeViewModel","Success->${_detailState.value.result}")
                }
                is Resource.Error->{
                    _detailState.value= DetailClass(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _detailState.value= DetailClass(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getArtist(token: String){
        artistUseCase.invoke(token,id).onEach { result->
            when(result){
                is Resource.Success ->{
                    _detailState.value= DetailClass(result =result.data)
                    resetValues()
                }
                is Resource.Error->{
                    _detailState.value= DetailClass(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _detailState.value= DetailClass(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPlaylist(token: String){
        playListUseCases.invoke(token,id).onEach { result->
            when(result){
                is Resource.Success ->{
                    _detailState.value= DetailClass(result =result.data)
                    resetValues()
                }
                is Resource.Error->{
                    _detailState.value= DetailClass(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _detailState.value= DetailClass(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun resetValues(){
        val contain=_detailState.value.result
        if(contain!=null){
            name=contain.name
            image=contain.artistImage
            type=contain.type
            artist=contain.artistName
        }
    }
}

data class DetailClass(
    val result: DetailModel? =null,
    val isLoading:Boolean=false,
    val error:String="",
)