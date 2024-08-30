package com.example.acoustic.spotify

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.material3.DatePicker
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.login.domain.repository.AuthRepository
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Date
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class SpotifyRepository @Inject constructor(private val sharedPref: SharedPref) {

    private lateinit var  authRequest:AuthorizationRequest

    fun authenticateUser(activity: Activity){
        authRequest= AuthorizationRequest.Builder(
            CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI
        ).setShowDialog(false)
            .setScopes(arrayOf("user-read-email","user-read-private","playlist-read-private"))
            .setCampaign("your-campaign-token")
            .build()
        AuthorizationClient.openLoginInBrowser(activity, authRequest)
        val dateTime= Date().time.milliseconds
        sharedPref.save(AUTH_TIME,dateTime.toString())
    }

    fun connectToRemote(token:String){

    }
    fun playSong(){

    }


    companion object {
        private const val CLIENT_ID="12a02e7930b14a748d832d05e668f146"
        private  const val  REDIRECT_URI="com.example.acoustic://callback"
        private const val  USER_TOKEN_KEY="USER_TOKEN"
        private const val  AUTH_TIME="LAST_AUTH_TIME"
        private const val  USER_ID="USER_ID"

    }
}