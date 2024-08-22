package com.example.acoustic.navigation.graph

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.acoustic.navigation.HomeNavigation
import com.example.acoustic.navigation.routes.GraphRoutes

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun RootNavGraph(activity:Activity) {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = GraphRoutes.AUTHENTICATION_GRAPH.route, route = GraphRoutes.ROOT_GRAPH.route ) {

        AuthGraph(navController = navController, activity )

        composable(route = GraphRoutes.HOME_NAVIGATION.route){
            HomeNavigation(activity,navController)
        }
    }
}



