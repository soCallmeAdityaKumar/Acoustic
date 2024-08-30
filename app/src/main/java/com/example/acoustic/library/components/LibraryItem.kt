package com.example.acoustic.library.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.acoustic.albumCard.components.DialogButton
import com.example.acoustic.library.model.LibraryModel
import com.example.acoustic.navigation.routes.Screens
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.albumBlackBackground

@Composable
fun LibraryItem(item:LibraryModel,navController: NavHostController) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)
        .wrapContentHeight()
        .clickable {
            val savedStateHandle: SavedStateHandle = SavedStateHandle()
            savedStateHandle.set("id",item.id)
            savedStateHandle.set("type",item.type)
            Log.d("getAlbum","type->${item.type}")
            navController.navigate(Screens.Detail.route.replace("{id}",item.id.toString()).replace("{type}",item.type.toString()))
        },
        elevation = CardDefaults.elevatedCardElevation(100.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(albumBlackBackground)
            .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Image(painter = rememberImagePainter(data = item.image), contentDescription ="image_library",
                Modifier
                    .size(80.dp)
                    .padding(vertical = 10.dp),contentScale = ContentScale.Fit )
            Text(text = item.name.toString(), modifier = Modifier
                .weight(0.8f)
                .wrapContentWidth()
                .fillMaxHeight(), textAlign = TextAlign.Start, color = Color.White, style = NavigationRowText.bodyMedium, fontSize = MaterialTheme.typography.titleMedium.fontSize)
            DialogButton(name=item.name.toString(),image=item.image.toString(),Modifier.weight(0.3f))
//            IconButton(onClick = { /*TODO navigate to the detail screen */ }) {
//                Icon(imageVector = Icons.Default.ArrowForward, contentDescription ="open_icon" , modifier = Modifier
//                    .weight(0.3f)
//                    .padding(horizontal = 5.dp))
//            }

        }
    }
}