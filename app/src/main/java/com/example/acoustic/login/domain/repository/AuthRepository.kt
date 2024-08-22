package com.example.acoustic.login.domain.repository

import android.app.Activity
import android.content.Context
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.spotify.SpotifyRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRepository @Inject constructor(@ApplicationContext val context: Context,private val spotifyRepository: SpotifyRepository) {

    fun register(activity: Activity) {
        try {
            spotifyRepository.authenticateUser(activity)
        }catch (e:Exception){
            throw e
        }
    }

    fun getTokenOfUser():String?{
        val sharedPref= SharedPref(context)
        if(sharedPref.ifContain("USER_TOKEN")){
            val token=sharedPref.value("USER_TOKEN")
            if(token==null)return null
            return token
        }else{
            return null
        }

    }



}

