package com.example.acoustic.home.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.acoustic.navigation.routes.Screens
import com.example.acoustic.ui.theme.NavigationRowText

@Composable
fun ArtistItem(
    name: String,
    url: String = "https://i.scdn.co/image/ab6761610000e5ebae07171f989fb39736674113",
    id: String,
    navController: NavHostController,
    type: String,
) {
     Column(modifier =
     Modifier
      .width(100.dp)
      .height(150.dp)
     .clip(RoundedCornerShape(20.dp))
      .clickable {
//          val savedStateHandle:SavedStateHandle=SavedStateHandle()
//          savedStateHandle.set("id",id)
//          savedStateHandle.set("type",type)
//          Log.d("getAlbum","type->$type")
          navController.navigate(Screens.Detail.route.replace("{id}",id).replace("{type}",type))
      }){
         Image(painter =  rememberImagePainter(data = url),
            contentDescription ="Artist_Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
             .size(100.dp)
             .padding(10.dp)
             .clip(CircleShape)
         )
         Text(text=name,
            modifier = Modifier
             .fillMaxWidth()
                .padding(5.dp),
             maxLines = 1,
            color = Color.White,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            style = NavigationRowText.bodyLarge)
     }
}