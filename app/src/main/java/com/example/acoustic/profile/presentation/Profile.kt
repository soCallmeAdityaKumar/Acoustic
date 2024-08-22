package com.example.acoustic.profile.presentation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.acoustic.R
import com.example.acoustic.navigation.routes.GraphRoutes
import com.example.acoustic.profile.UserViewModel
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.appCustomBackground

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Profile(
    navController: NavHostController,
    userViewModel: UserViewModel= hiltViewModel(),
) {
    val user=userViewModel.state.value
        Column(modifier = Modifier
            .fillMaxSize()
            .background(appCustomBackground)) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black)
            ){

                Column(Modifier.fillMaxSize()){
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
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.memoji),
                            contentDescription ="user_logo",
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
                        Text(
                            text = user.userDetail?.display_name.toString().capitalize(),
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            style = NavigationRowText.bodyLarge,
                            color = Color.White
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Followers : ",
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            style = NavigationRowText.bodyLarge,
                            color = Color.White,
                        )
                        Text(
                            text = user.userDetail?.followers?.total.toString(),
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            style = NavigationRowText.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }
            if(!user.userDetail?.email.isNullOrBlank()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
                    Text(modifier = Modifier.width(60.dp), text = "Email : ", color = Color.White ,style = NavigationRowText.bodyLarge, fontSize = MaterialTheme.typography.labelLarge.fontSize)
                    Text(text = user.userDetail?.email.toString(), color = Color.White, style = NavigationRowText.bodyLarge, fontSize = MaterialTheme.typography.labelLarge.fontSize)
                }
            }
            if(!user.userDetail?.country.isNullOrBlank()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
                    Text(modifier = Modifier,text = "Country : ", color = Color.White,style = NavigationRowText.bodyLarge,fontSize = MaterialTheme.typography.labelLarge.fontSize)
                    Text(text = user.userDetail?.country.toString(), color = Color.White,style = NavigationRowText.bodyLarge,fontSize = MaterialTheme.typography.labelLarge.fontSize)
                }
            }
            if(!user.userDetail?.type.isNullOrBlank()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
                    Text(modifier = Modifier.width(60.dp),text = "Type : ", color = Color.White,style = NavigationRowText.bodyLarge, fontSize = MaterialTheme.typography.labelLarge.fontSize)
                    Text(text = user.userDetail?.type.toString().capitalize(), color = Color.White,style = NavigationRowText.bodyLarge,fontSize = MaterialTheme.typography.labelLarge.fontSize)
                }
            }
            if(!user.userDetail?.id.isNullOrBlank()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
                    Text(modifier = Modifier,text = "ID : ", color = Color.White,style = NavigationRowText.bodyLarge,fontSize = MaterialTheme.typography.labelLarge.fontSize)
                    Text(text = user.userDetail?.id.toString(), color = Color.White,style = NavigationRowText.bodyLarge,fontSize = MaterialTheme.typography.labelLarge.fontSize)
                }
            }

            if(user.userDetail!=null && user.userDetail.images.isNotEmpty()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),)
                {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(user.userDetail.images.size) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            if(user.userDetail!=null&& user.userDetail.product.isNotEmpty()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
                    Text(modifier = Modifier, text = "Product : ", color = Color.White ,style = NavigationRowText.bodyLarge, fontSize = MaterialTheme.typography.labelLarge.fontSize)
                    Text(text = user.userDetail?.product.toString(), color = Color.White, style = NavigationRowText.bodyLarge, fontSize = MaterialTheme.typography.labelLarge.fontSize)
                }
            }
            Button(modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Change Password",style = NavigationRowText.bodyLarge,)
            }

        }

}
