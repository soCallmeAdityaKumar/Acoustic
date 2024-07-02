package com.example.acoustic.login.presentation

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.acoustic.R
import com.example.acoustic.navigation.Screen
import com.example.acoustic.navigation.auth.AuthRoute
import com.example.acoustic.ui.theme.Acoustic
import com.example.acoustic.ui.theme.loginButtonColor


@Composable
fun LoginScreen (
    navController: NavHostController,
    activity: Activity,
    viewModel: AuthViewModel= hiltViewModel()
){
    Log.d("Login","Activity viewModel->${viewModel}")
    val authState by viewModel.authState.observeAsState(null)


        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)){

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.curtis),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Transparent
                                ),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )
            }
            Text(
                text = "Acoustic",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, bottom = 20.dp),
                textAlign = TextAlign.Center,
                style = Acoustic.bodyLarge,
                color = Color.White
            )
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), verticalArrangement = Arrangement.Bottom) {

                Text(
                    text = "Create Your PlayList\nShare it With Others",
                    modifier = Modifier
                        .width(340.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 15.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "Choose music according to your taste, create your own \nplaylist to accompany your day!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 20.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = loginButtonColor,
                        contentColor = Color.White
                    ),
                    onClick = {
                    Log.d("LoginScreen","LoginScreen: Button Click")
                    viewModel.login(activity)
                }
                ) {
                    when (authState) {
                        is AuthState.Success -> {
                            Text(
                                text = "Getting Things Done...",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                            navController.navigate(Screen.HOME_NAVIGATION.route) {
                                popUpTo(Screen.AUTHENTICATION_GRAPH.route) {
                                    inclusive = true
                                }
                            }
                        }

                        is AuthState.Loading -> {
                            Text(
                                text = "Loading",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        }

                        else -> {
                            Text(
                                text = "Get Started",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineLarge,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        }

                    }

                }
            }
        }

}
