package com.example.acoustic.detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.acoustic.common.TYPE
import com.example.acoustic.albumCard.AlbumItemBox
import com.example.acoustic.detail.viemModel.DetailViewModel
import com.example.acoustic.navigation.routes.GraphRoutes
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.albumBlackBackground
import com.example.acoustic.ui.theme.loginButtonColor
import com.example.acoustic.ui.theme.montserrat

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Detail(
    navController: NavHostController,
    id:String,
    type:String,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val scrollState= rememberScrollState()
    var itemtoShow by remember{ mutableStateOf(5) }
    var showTrack by remember{ mutableStateOf(true) }


    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .height(300.dp)
                .wrapContentHeight()
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(albumBlackBackground)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                IconButton(onClick = {
                    navController.navigate(GraphRoutes.HOME_GRAPH.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                })
                {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        tint = Color.White
                    )
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Image(
                        painter = rememberImagePainter(data = detailViewModel.image.toString()),
                        contentDescription = "album_image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(90.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material3.Text(
                        text = detailViewModel.name.toString(),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        style = NavigationRowText.bodyLarge,
                        color = loginButtonColor
                    )
                }
                if(type.uppercase()==TYPE.ARTIST.toString()) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "By ",
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            style = NavigationRowText.bodyLarge,
                            color = Color.White,
                        )
                        Text(
                            text = detailViewModel.artist.toString(),
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            style = NavigationRowText.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        if(!detailViewModel.detailState.value.result?.list.isNullOrEmpty()){
            Text(
                "Album", color = Color.White,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                style = NavigationRowText.bodyLarge,
                modifier = Modifier.padding(10.dp)
            )
            if (detailViewModel.detailState.value.result?.list?.isEmpty() == false) {

                var count=0
                detailViewModel.detailState.value.result?.list?.take(itemtoShow)?.forEach {
                    AlbumItemBox(navController,item = it, detailViewModel.detailState.value.result?.image!![count++])
                }


            }

            if(itemtoShow>=detailViewModel.detailState.value.result?.list?.size!!){
                showTrack=false
            }
            if (showTrack) {
                Text(text = "Show More", Modifier.padding(bottom = 60.dp, start = 15.dp).clickable {
                    if (itemtoShow + 5 > detailViewModel.detailState.value.result?.list?.size!!) {
                        itemtoShow = detailViewModel.detailState.value.result?.list?.size!!
                        showTrack=false
                    } else {
                        itemtoShow += 5
                        if(itemtoShow>=detailViewModel.detailState.value.result?.list?.size!!){
                            showTrack=false
                        }
                    }
                }, fontSize =MaterialTheme.typography.bodyLarge.fontSize, color = Color.White, fontFamily = montserrat)
            }
        }
        }

}

