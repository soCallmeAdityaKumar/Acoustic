package com.example.acoustic

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.login.domain.repository.AuthRepository
import com.example.acoustic.login.presentation.AuthViewModel
import com.example.acoustic.navigation.RootNavGraph
import com.example.acoustic.ui.theme.AcousticTheme
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val  authViewModel:AuthViewModel  by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AcousticTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Log.d(TAG,innerPadding.toString())
                    RootNavGraph(this@MainActivity)
                }
            }
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = intent.data
        Log.d("MAIN Activity","Activity onNewIntent ")

        if (uri != null) {
            val response = AuthorizationResponse.fromUri(uri)
            when(response.type){
                AuthorizationResponse.Type.TOKEN->{
                    val sharedPref=SharedPref(this)
                    sharedPref.save(response.accessToken,"USER_TOKEN")
                    Log.d("MAIN Activity","token->${response.accessToken}")
                }
                else->{
                    Log.d("MAIN_ACTIVITY","onNewIntent::${AuthorizationResponse.Type.ERROR}")
                }
            }
        }
    }

}

