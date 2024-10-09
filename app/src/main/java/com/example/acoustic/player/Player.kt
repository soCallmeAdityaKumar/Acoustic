package com.example.acoustic.player

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
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
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.acoustic.R
import com.example.acoustic.albumCard.AlbumItem
import com.example.acoustic.player.components.ImageCard
import com.example.acoustic.player.viewmodel.PlayerViewModel
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.albumBlackBackground
import com.example.acoustic.ui.theme.loginButtonColor
import com.example.acoustic.ui.theme.montage
import com.example.acoustic.ui.theme.montserrat

@OptIn(ExperimentalFoundationApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Player(id:String?) {

    var playOrpause = remember {
        mutableStateOf(0)
    }

    var likedOrUnliked = remember {
        mutableStateOf(true)
    }

    Log.d("Player","Player->${id.toString()}")

    val viewModel: PlayerViewModel= hiltViewModel()
    viewModel.id=id.toString()

    val track=viewModel.track.value

    LaunchedEffect(key1 = viewModel.id) {
        viewModel.getTracks(id.toString())
    }


    if(track.success!=null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
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



            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Center
            ) {
                var artists = ""
                for (i in track.success.artists) {
                    artists += i.name
                    artists += ", ";
                }
                artists.dropLast(2)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = track.success.name,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontFamily = montserrat,
                        color = loginButtonColor,
                        softWrap = false,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .padding(horizontal = 0.dp)
                            .basicMarquee(2, animationMode = MarqueeAnimationMode.Immediately),
                    )
                    Text(
                        text = "By " + track.success.artists[0].name,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontFamily = montserrat,
                        color = Color.White,
                    )
                }

            }


            ImageCard(
                url = track.success!!.album.images[0].url, modifier = Modifier
                    .size(450.dp)
            )
            

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(20.dp)), backgroundColor = albumBlackBackground,) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier) {
                            Icon(
                                painterResource(id = R.drawable.skip_back),
                                contentDescription = "rewind",
                                tint = Color.White
                            )
                        }

                        IconButton(onClick = {
                            if (playOrpause.value == 1) playOrpause.value = 0
                            else playOrpause.value = 1
                        }) {
                            if (playOrpause.value == 1) {
                                Icon(
                                    painterResource(id = R.drawable.play),
                                    contentDescription = "forward",
                                    tint = Color.White
                                )
                            } else {
                                Icon(
                                    painterResource(id = R.drawable.pause),
                                    contentDescription = "forward",
                                    tint = Color.White
                                )
                            }

                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painterResource(id = R.drawable.skip_forward),
                                contentDescription = "play",
                                tint = Color.White
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .size(4.dp)
                        .padding(horizontal = 20.dp)
                        .background(Color.Gray))

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {

                        IconButton(modifier = Modifier.padding(horizontal = 20.dp), onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(id = R.drawable.list_check), contentDescription = "list", tint = Color.White)
                        }

                        IconButton(modifier = Modifier.padding(horizontal = 20.dp),onClick = {
                            if(likedOrUnliked.value)likedOrUnliked.value=false
                            else likedOrUnliked.value=true
                        }) {
                            if(likedOrUnliked.value){
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "list", tint = Color.White)
                            }else{
                                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "list", tint = Color.White)
                            }
                        }
                    }
                }

            }
            
        }
    }

}