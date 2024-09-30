package com.example.acoustic.player

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.acoustic.R
import com.example.acoustic.albumCard.AlbumItem
import com.example.acoustic.player.components.ImageCard
import com.example.acoustic.player.viewmodel.PlayerViewModel
import com.example.acoustic.ui.theme.NavigationRowText

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Player(id:String?) {

    Log.d("Track",id.toString())
    var viewModel:PlayerViewModel= hiltViewModel()
    var track=viewModel.track.value

    LaunchedEffect(key1 = id) {
       if(id!=null){
           viewModel.getTracks(id)
           track=viewModel.track.value
       }
    }

    if(track.success!=null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {

                    },
                    Modifier.weight(0.5f)
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "down_arrow",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Playing",
                    Modifier
                        .fillMaxWidth()
                        .weight(3f),
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    style = NavigationRowText.bodyMedium,
                    color = Color.White
                )

                IconButton(
                    onClick = {

                    },
                    Modifier.weight(0.5f)
                ) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "down_arrow",
                        tint = Color.White
                    )
                }
            }

                ImageCard(url = track.success!!.album.images[0].url, modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp))


        }
    }

}