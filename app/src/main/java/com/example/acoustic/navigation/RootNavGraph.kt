package com.example.acoustic.navigation

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.acoustic.navigation.auth.AuthGraph
import com.example.acoustic.navigationDrawer.domain.model.NavigationDrawerGraph
import com.example.acoustic.navigationDrawer.domain.model.NavigationDrawerScreen
import com.example.acoustic.profile.presentation.Profile
import com.example.acoustic.settings.Settings

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun RootNavGraph(activity:Activity) {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AUTHENTICATION_GRAPH.route, route =Screen.ROOT_GRAPH.route ) {

        AuthGraph(navController = navController, activity )

        composable(route = Screen.HOME_NAVIGATION.route){
            HomeNavigation(activity,navController)
        }
    }
}


sealed class Screen(val route:String){
    data object AUTHENTICATION_GRAPH:Screen("auth_graph")
    data object ROOT_GRAPH:Screen("root_graph")
    data object  HOME_GRAPH:Screen("home_graph")
    data object HOME_NAVIGATION:Screen("home_navigations")
     data object NAVIGATION_DRAWER_GRAPH:Screen("navigation_drawer_graph")
    data object BOTTOM_BAR_GRAPH:Screen("bottom_bar_graph")


}
