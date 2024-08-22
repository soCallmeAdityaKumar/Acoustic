package com.example.acoustic.home.viewModels

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acoustic.common.Resource
import com.example.acoustic.data.dto.severalArtists.Artists
import com.example.acoustic.data.dto.categories.Categories
import com.example.acoustic.data.dto.new_releases.Releases
import com.example.acoustic.home.useCases.NewReleasesAlbumCases
import com.example.acoustic.home.useCases.SeveralArtistsUseCases
import com.example.acoustic.home.useCases.SeveralCategories
import com.example.acoustic.login.domain.data.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val severalArtistsUseCases: SeveralArtistsUseCases,
    private val severalCategories: SeveralCategories,
    private val newReleasesAlbumCases: NewReleasesAlbumCases,
    @ApplicationContext private val context: Context
) :ViewModel() {

    private val _artistsState= mutableStateOf(SeveralArtistsState())
    val artistState: State<SeveralArtistsState> = _artistsState

    private val _categoriesState= mutableStateOf(SeveralCategoriesState())
    val categoriesState: State<SeveralCategoriesState> = _categoriesState

    private val _newAlbums= mutableStateOf(NewReleases())
    val newAlbums: State<NewReleases> = _newAlbums



    init {
        val sharedPref= SharedPref(context)
        val token=sharedPref.value("USER_TOKEN")
        if (token != null) {
            CoroutineScope(Dispatchers.IO).launch {
            getSeveralArtists(token,listOf("2CIMQHirSU0MQqyYHq0eOx","57dN52uHvrHOxijzpIgu3E","1vCWHaC5f2uS3yhpwWbIA6"))
            getSeveralCategories(token)
            getNewReleasesAlbum(token)
        }
        }
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getSeveralArtists(token:String,id:List<String>){
        severalArtistsUseCases.invoke(token,id).onEach {result->
            Log.d("getSeveralArtists","result->${result}")
            when(result){
                is Resource.Success ->{
                    _artistsState.value= SeveralArtistsState(result=result.data)
                    Log.d("HomeViewModel","Success->${_artistsState.value.result}")
                }
                is Resource.Error->{
                    _artistsState.value= SeveralArtistsState(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _artistsState.value= SeveralArtistsState(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope)
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getSeveralCategories(token:String){
        severalCategories.invoke(token).onEach {result->
            Log.d("getSeveralCategories","result->${result}")
            when(result){
                is Resource.Success<*> ->{
                    _categoriesState.value= SeveralCategoriesState(result=result.data)
                    Log.d("HomeViewModel","Success->${_categoriesState.value.result}")
                }
                is Resource.Error->{
                    _categoriesState.value= SeveralCategoriesState(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _categoriesState.value= SeveralCategoriesState(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getNewReleasesAlbum(token:String){
        newReleasesAlbumCases.invoke(token).onEach {result->
            Log.d("getNewReleasesAlbum","result->${result}")
            when(result){
                is Resource.Success<*> ->{
                    _newAlbums.value= NewReleases(result=result.data)
                    Log.d("HomeViewModel","Success->${_artistsState.value.result}")
                }
                is Resource.Error->{
                    _newAlbums.value= NewReleases(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _newAlbums.value= NewReleases(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}

data class SeveralArtistsState(
    val result: Artists?=null,
    val isLoading:Boolean=false,
    val error:String="",
)

data class SeveralCategoriesState(
    val result: Categories?=null,
    val isLoading:Boolean=false,
    val error:String="",
)
data class NewReleases(
    val result: Releases?=null,
    val isLoading:Boolean=false,
    val error:String="",
)

