package com.example.acoustic.library.domain

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acoustic.common.Resource
import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.library.data.UserPlaylists
import com.example.acoustic.library.useCases.LibraryUseCases
import com.example.acoustic.login.domain.data.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryUseCases: LibraryUseCases,
    @ApplicationContext private val context: Context
):ViewModel() {

    private val _state= mutableStateOf(PlayListsState())
    val state: State<PlayListsState> = _state


    init {
        val sharedPref= SharedPref(context)
        val token=sharedPref.value("USER_TOKEN")
        if(token!=null){
            Log.d("Library","token->$token")
            getUserPlaylists(token)
        }
    }

    private fun getUserPlaylists(token:String){
        libraryUseCases.invoke(token).onEach {result->
            Log.d("Library","result->${result}")
            when(result){
                is Resource.Success<*> ->{
                    _state.value=PlayListsState(playList = result.data)
                }
                is Resource.Error->{
                    _state.value=PlayListsState(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _state.value=PlayListsState(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope)
    }
}

data class PlayListsState(
    val isLoading:Boolean=false,
    val error:String="",
    val playList: UserPlaylists?=null
)
