package com.example.acoustic.albumCard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.albumBlackBackground
import com.example.acoustic.ui.theme.appCustomBackground
import com.example.acoustic.ui.theme.loginButtonColor

@Composable
fun AlbumDialog(text:String,image:String,onDismiss:()->Unit) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .wrapContentHeight(unbounded = true)
                .width(250.dp)
                .background(appCustomBackground, shape = RoundedCornerShape(16.dp))
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = rememberImagePainter(data = image),
                        contentDescription = "image_dialog",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(10.dp)
                            .clip(CircleShape), contentScale = ContentScale.Crop)
                    Text(text = text, color = loginButtonColor,fontSize = 20.sp,
                        style = NavigationRowText.bodyLarge)
                }

//                TextButton(onClick = { onDismiss() }) {
//                    Text(text = "Play Now", color = Color.White,fontSize = MaterialTheme.typography.bodySmall.fontSize,
//                        style = NavigationRowText.bodyLarge)
//                   } 
                TextButton(onClick = { onDismiss()}, elevation = ButtonDefaults.elevation(150.dp)) {
                    Text(text = "Add to PlayList", color = Color.White,fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        style = NavigationRowText.bodyLarge)
                }
                TextButton(onClick = { onDismiss() }, elevation = ButtonDefaults.elevation(150.dp)) {
                    Text(text = "Play Next", color = Color.White,fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        style = NavigationRowText.bodyLarge)
                }
                TextButton(onClick = { }, elevation = ButtonDefaults.elevation(150.dp)) {
                    Text(text = "Add To Queue", color = Color.White,fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        style = NavigationRowText.bodyLarge)
                }

            }
    }
    }
}