package com.example.acoustic.player.components

import android.speech.ModelDownloadListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ImageCard(url:String,modifier:Modifier=Modifier) {
    Card (modifier = modifier.padding(20.dp), elevation = CardDefaults.elevatedCardElevation(20.dp)){
        Box(modifier=Modifier.size(550.dp)) {
            Image(
                painter = rememberImagePainter(data = url),
                contentDescription = "image_card",
                modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
            )
        }
    }
}