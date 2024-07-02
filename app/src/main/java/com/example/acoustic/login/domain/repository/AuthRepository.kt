package com.example.acoustic.login.domain.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.login.presentation.AuthState
import com.example.acoustic.login.presentation.AuthViewModel
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRepository @Inject constructor(@ApplicationContext val context: Context) {

    private lateinit var  authRequest:AuthorizationRequest

    fun authenticateUser(activity: Activity){
        authRequest= AuthorizationRequest.Builder(
            CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI
        ).setShowDialog(false)
            .setScopes(arrayOf("user-read-email","user-read-private","playlist-read-private"))
            .setCampaign("your-campaign-token")
            .build()
        AuthorizationClient.openLoginInBrowser(activity, authRequest)
        Log.d("Repos",AuthorizationResponse.Type.values().toString())
    }

    fun getTokenOfUser():String?{
        Log.d("AuthRepository","getTokenOfUser")
        val sharedPref= SharedPref(context)
        if(sharedPref.ifContain("USER_TOKEN")){
            val token=sharedPref.value("USER_TOKEN")
            if(token==null)return null
            return token
        }else{
            return null
        }

    }

    companion object {
        private const val CLIENT_ID="12a02e7930b14a748d832d05e668f146"
        private  const val  REDIRECT_URI="com.example.acoustic://callback"
        private const val  USER_TOKEN_KEY="USER_TOKEN"
    }

}

