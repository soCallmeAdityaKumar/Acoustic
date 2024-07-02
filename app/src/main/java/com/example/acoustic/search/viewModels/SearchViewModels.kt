package com.example.acoustic.search.viewModels

import android.content.Context
import android.os.Build
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.acoustic.common.Resource
import com.example.acoustic.library.data.UserPlaylists
import com.example.acoustic.library.domain.PlayListsState
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.search.data.album.Albums
import com.example.acoustic.search.data.track.Tracks
import com.example.acoustic.search.useCases.SearchAlbumUseCases
import com.example.acoustic.search.useCases.SearchTrackUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModels @Inject constructor(
    private val searchAlbumUseCases: SearchAlbumUseCases,
    private val searchTrackUseCases: SearchTrackUseCases,
    @ApplicationContext private val context: Context
): ViewModel() {

    var _searchQuery by mutableStateOf("utopia")
        private set

    var type by mutableStateOf("album")
        private set

    fun onSearchQueryChange(newQuery:String){
        _searchQuery=newQuery
    }

    fun onTypeChange(newType:String){
        type=newType
    }

    private val _searchResult= mutableStateOf(ResultsState())
    val searchResult:State<ResultsState> = _searchResult

    var _token = ""
        private set

    init {
        val sharedPref= SharedPref(context)
        val token=sharedPref.value("USER_TOKEN")
        if(token!=null){
            Log.d("Search","token->$token")
            _token=token
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun onSearch(){
        when(type){
            TYPE.album.toString() ->{
                getSearchAlbums(token = _token,_searchQuery)
                Log.d("Search_ViewModel",_searchQuery)
            }
            TYPE.track.toString()->{
                getSearchTrack(token = _token,_searchQuery)
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getSearchAlbums(token:String, albums:String){
        searchAlbumUseCases.invoke(token,albums).onEach {result->
            Log.d("Search_Album","result->${result.data}")
            when(result){
                is Resource.Success<*> ->{
                    _searchResult.value= ResultsState(DATA(album = result.data))
                }
                is Resource.Error->{
                    _searchResult.value= ResultsState(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _searchResult.value= ResultsState(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope)
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getSearchTrack(token:String, track:String){
        searchTrackUseCases.invoke(token,track).onEach {result->
            Log.d("Search_Track","result->${result}")
            when(result){
                is Resource.Success<*> ->{
                    _searchResult.value= ResultsState(DATA(track = result.data))
                }
                is Resource.Error->{
                    _searchResult.value= ResultsState(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _searchResult.value= ResultsState(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope)
    }
}

enum class TYPE{
    album,track
}

data class ResultsState(
    val data:DATA?=null,
    val isLoading:Boolean=false,
    val error:String="",
)

data class DATA(
    val album:Albums?=null,
    val track:Tracks?=null,
)