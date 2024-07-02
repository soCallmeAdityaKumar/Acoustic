package com.example.acoustic.navigation.home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.acoustic.navigation.Screen
import com.example.acoustic.navigation.bottomBarNav.BottomNavBarScreens
import com.example.acoustic.home.Home
import com.example.acoustic.library.Library
import com.example.acoustic.navigationDrawer.domain.model.NavigationDrawerGraph
import com.example.acoustic.navigationDrawer.domain.model.NavigationDrawerScreen
import com.example.acoustic.profile.presentation.Profile
import com.example.acoustic.search.Search
import com.example.acoustic.settings.Settings

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        route = Screen.HOME_GRAPH.route,
        startDestination = Screen.BOTTOM_BAR_GRAPH.route
    ) {
        navigation(startDestination = BottomNavBarScreens.Home.route,route=Screen.BOTTOM_BAR_GRAPH.route){
            composable(route=BottomNavBarScreens.Home.route){
                Home(navController = navController)
            }

            composable(route=BottomNavBarScreens.Search.route){
                Search(navController = navController)
            }

            composable(route=BottomNavBarScreens.Library.route){
                Library(navController = navController)
            }
        }

            composable(route=NavigationDrawerScreen.Profile.route){
                Profile(navController)
            }
            composable(route=NavigationDrawerScreen.Settings.route){
                Settings()
            }
    }

}