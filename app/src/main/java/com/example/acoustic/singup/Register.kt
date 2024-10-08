package com.example.acoustic.singup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.acoustic.navigation.routes.GraphRoutes

@Composable
fun Register (navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Register", modifier = Modifier
            .clickable {
                navController.navigate(GraphRoutes.HOME_NAVIGATION.route){
                    popUpTo(GraphRoutes.AUTHENTICATION_GRAPH.route){
                        inclusive=true
                    }
                }
            }, fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Back", modifier = Modifier
            .clickable {
                navController.popBackStack()
            }, fontSize = MaterialTheme.typography.headlineLarge.fontSize)
    }
}