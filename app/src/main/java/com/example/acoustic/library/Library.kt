package com.example.acoustic.library

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.acoustic.library.domain.LibraryViewModel
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Library(
    navController: NavHostController,
    viewModel:LibraryViewModel= hiltViewModel()
) {
    val userPlaylists=viewModel.state.value
    Log.d("Library","library->${if(userPlaylists.playList!=null)userPlaylists.playList.items else "Showing null"}")

}