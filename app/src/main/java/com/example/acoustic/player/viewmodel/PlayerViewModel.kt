package com.example.acoustic.player.viewmodel

import android.media.session.MediaSession.Token
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acoustic.common.Resource
import com.example.acoustic.common.SHARED_PREF
import com.example.acoustic.data.dto.track.Track
import com.example.acoustic.detail.useCases.ArtistAlbumUseCase
import com.example.acoustic.library.viewModel.PlayListsState
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.player.useCases.TrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val trackUseCase: TrackUseCase,
    private val sharedPref: SharedPref
):ViewModel() {

    private val _track= mutableStateOf(TrackState())
    val track:MutableState<TrackState>  = _track

     var token:String=""



    init {
        if(sharedPref.ifContain(SHARED_PREF.USER_TOKEN.toString())){
            token=sharedPref.value(SHARED_PREF.USER_TOKEN.toString()).toString()
            Log.d("Track",token+"all")

        }
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getTracks(id: String){
        trackUseCase.invoke(token,id).onEach {result->
            Log.d("Track","result->${result}")
            when(result){
                is Resource.Success ->{
                    _track.value= TrackState(success = result.data)
                }
                is Resource.Error->{
                    _track.value= TrackState(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _track.value= TrackState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}



data class TrackState(
    val isLoading:Boolean=false,
    val success:Track?=null,
    val error:String?=null
)