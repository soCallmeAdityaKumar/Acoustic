package com.example.acoustic.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.acoustic.common.TYPE
import com.example.acoustic.navigation.routes.Screens
import com.example.acoustic.search.data.album.Item
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.albumBlackBackground

@Composable
fun AlbumCard(item: Item, navController: NavHostController) {
    Box(modifier = Modifier
        .clickable {
            navController.navigate(Screens.Detail.route.replace("{id}",item.id).replace("{type}",
                TYPE.ALBUM.toString()))
        }
        .clip(RoundedCornerShape(10.dp))
        .background(albumBlackBackground)
    ){
        Column {
            Image(painter = rememberImagePainter(data = item.images[0].url),
                contentDescription = "Album_Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)

            )
            Text(modifier = Modifier.padding(start = 5.dp, top = 10.dp), text = item.name, color = Color.White,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                style = NavigationRowText.bodyLarge,)
            Row(){
                Text(modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp),text = item.album_type.uppercase()+":",color = Color.White,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    style = NavigationRowText.bodyLarge,maxLines = 1)
                Text(
                    text = item.artists[0].name,color = Color.White,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    style = NavigationRowText.bodyLarge, maxLines = 1, modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp))
            }
        }

    }
}