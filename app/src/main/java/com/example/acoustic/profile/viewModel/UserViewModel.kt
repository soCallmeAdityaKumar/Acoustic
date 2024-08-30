package com.example.acoustic.profile.viewModel

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
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.profile.useCases.GetUserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCases: GetUserUseCases,
    @ApplicationContext private val context:Context
) :ViewModel(){

    private val _state= mutableStateOf(UserState())
    val state: State<UserState> = _state


    init {
        val sharedPref=SharedPref(context)
        val token=sharedPref.value("USER_TOKEN")
        if(token!=null){
            Log.d("User","token->$token")
            getUser(token)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getUser(token:String){
        getUserUseCases.invoke(token).onEach {result->
            Log.d("User","result->${result}")
            when(result){
                is Resource.Success<*> ->{
                    _state.value= UserState(userDetail = result.data)
                }
                is Resource.Error->{
                    _state.value= UserState(error = result.message?:"An unexpected error occurred ")
                }
                is Resource.Loading->{
                    _state.value= UserState(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope)
    }
}

data class UserState(
    val isLoading:Boolean=false,
    val error:String="",
    val userDetail:User_Detail?=null
)
