package com.example.acoustic.artist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.acoustic.navigation.routes.GraphRoutes
import com.example.acoustic.ui.theme.albumBlackBackground

@Composable
fun Artist(
    navController: NavController,
    id:String
) {
    Column (modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(albumBlackBackground)){
            IconButton(onClick = {
                navController.navigate(GraphRoutes.HOME_GRAPH.route){
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop=true
                }
            })
            {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription ="Arrow Back",
                    tint = Color.White
                )
            }
        }
//        Image(painter = rememberImagePainter(data = ))
        Text(id, modifier = Modifier.padding(100.dp))
    }
}