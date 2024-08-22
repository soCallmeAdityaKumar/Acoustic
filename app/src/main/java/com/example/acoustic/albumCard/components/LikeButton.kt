package com.example.acoustic.albumCard.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LikeButton(liked:Boolean,modifier: Modifier) {
    IconButton(onClick = { /*TODO*/ },
        modifier = modifier
        .fillMaxHeight()
            .padding(10.dp)) {
        if(liked){
            Icon(imageVector =Icons.Default.Favorite , contentDescription ="liked_button", tint = Color.White )
        }else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "not_liked_button",
                tint = Color.White
            )

        }
    }
}