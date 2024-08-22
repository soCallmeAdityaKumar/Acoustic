package com.example.acoustic.navigation.graph

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.acoustic.artist.Artist
import com.example.acoustic.navigation.routes.BottomNavBarScreens
import com.example.acoustic.home.Home
import com.example.acoustic.library.Library
import com.example.acoustic.navigation.routes.GraphRoutes
import com.example.acoustic.navigation.routes.NavigationDrawerScreen
import com.example.acoustic.profile.presentation.Profile
import com.example.acoustic.search.Search
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