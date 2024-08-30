package com.example.acoustic.navigation.graph

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.acoustic.navigation.routes.GraphRoutes
import com.example.acoustic.navigation.routes.NavigationDrawerScreen
import com.example.acoustic.profile.Profile
import com.example.acoustic.settings.Settings

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        route = GraphRoutes.HOME_GRAPH.route,
        startDestination = GraphRoutes.BOTTOM_BAR_GRAPH.route
    ) {

        HomeGraph(navController)

        composable(route= NavigationDrawerScreen.Profile.route){
            Profile(navController)
        }
        composable(route= NavigationDrawerScreen.Settings.route){
            Settings()
        }
    }

}