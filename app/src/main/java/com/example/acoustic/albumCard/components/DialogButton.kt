package com.example.acoustic.albumCard.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DialogButton(name:String,image:String,modifier: Modifier) {
    var showDialog by remember{
        mutableStateOf(false)
    }
    IconButton(onClick = { showDialog=!showDialog },
        modifier = modifier
            .fillMaxHeight()
            .padding(10.dp)) {
        Icon(imageVector = Icons.Default.List, contentDescription ="dialog_button", tint = Color.White )
    }
    if(showDialog){
        AlbumDialog(name,image){
            showDialog=!showDialog
        }
    }
}