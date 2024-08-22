package com.example.acoustic.login.presentation

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acoustic.login.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
):ViewModel() {

    private  var _authState= MutableLiveData<AuthState>()
    val authState: LiveData<AuthState>  get()= _authState

    init {
        viewModelScope.launch {
            token()
        }
    }
    fun login(activity: Activity){
        viewModelScope.launch {
            _authState.value=(AuthState.Loading(true))
            authRepository.register(activity)
            token()
        }
    }

    suspend fun token(){
        if(authRepository.getTokenOfUser()==null){
            delay(2000)
            _authState.value=(AuthState.Error("Cannot get token !! Try Again"))
            token()
        }
        else{
            val token=authRepository.getTokenOfUser()
            if(token!=null){
                _authState.value=(AuthState.Success(token))
            }
            else{
                _authState.postValue(AuthState.Error("Cannot get token !! Try Again"))
            }
        }

    }




}

sealed class AuthState(){
    data class Success(var token:String):AuthState()
    data class  Error(var error:String):AuthState()
    data class  Loading(var isLoading: Boolean):AuthState()
}