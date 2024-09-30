package com.example.acoustic.albumCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.acoustic.albumCard.components.DialogButton
import com.example.acoustic.albumCard.components.LikeButton
import com.example.acoustic.navigation.routes.Screens
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.albumBlackBackground


@Composable
fun AlbumItemBox(navController: NavHostController, item: AlbumItem, image:String){

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(5.dp)
        .clip(RoundedCornerShape(10.dp)),
        elevation = 20.dp,
        backgroundColor = albumBlackBackground) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
        ){
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
                Image(painter = rememberImagePainter(data =image ), contentDescription ="album_image",
                    modifier = Modifier
                        .size(80.dp)
                        .weight(20f)
                        .padding(10.dp)
                        .clip(CircleShape), contentScale = ContentScale.Crop)
                Column(modifier = Modifier
                    .clickable {
                        navController.navigate(Screens.Player.route.replace("{id}",item.id))
                    }
                    .fillMaxHeight()
                    .weight(50f)
                    .padding(5.dp), verticalArrangement = Arrangement.Center) {
                    Text(text = item.name, color = Color.White,fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        style = NavigationRowText.bodyLarge)
                    Text(text = item.artists,color = Color.White,fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        style = NavigationRowText.bodyLarge)
                }
                LikeButton(liked = true,Modifier.weight(10f))
                DialogButton(name=item.name,image=image,Modifier.weight(20f))
            }
        }
    }
}

data class AlbumItem(val name:String,val id:String, val artists:String)

