package com.example.acoustic.navigationDrawer.domain.model

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.acoustic.navigation.Screen
import com.example.acoustic.profile.presentation.Profile
import com.example.acoustic.settings.Settings

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavigationDrawerGraph (navController: NavHostController= rememberNavController()){
    NavHost(navController = navController, startDestination =NavigationDrawerScreen.Profile.route, route = Screen.NAVIGATION_DRAWER_GRAPH.route ) {
        composable(route=NavigationDrawerScreen.Profile.route){
            Profile(navController)
        }
        composable(route=NavigationDrawerScreen.Settings.route){
            Settings()
        }
    }
}

