package com.example.acoustic.library

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.acoustic.albumCard.AlbumItemBox
import com.example.acoustic.library.components.LibraryItem
import com.example.acoustic.library.components.LibraryVariedItem
import com.example.acoustic.library.model.toLibraryModel
import com.example.acoustic.library.viewModel.LibraryViewModel
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.loginButtonColor

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Library(
    navController: NavHostController,
    viewModel:LibraryViewModel= hiltViewModel()
) {
    val userPlaylists=viewModel.state.value


    Log.d("Library","library->${if(userPlaylists.playList!=null)userPlaylists.playList.items else "Showing null"}")
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 95.dp, start = 10.dp),
        ){
        userPlaylists.playList?.items?.let { list ->
            LibraryVariedItem(title = "Your Playlists", list = list, navController = navController)
        }


    }
}

