package com.example.acoustic.navigation.graph

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.acoustic.detail.Detail
import com.example.acoustic.home.Home
import com.example.acoustic.library.Library
import com.example.acoustic.navigation.routes.BottomNavBarScreens
import com.example.acoustic.navigation.routes.GraphRoutes
import com.example.acoustic.navigation.routes.Screens
import com.example.acoustic.search.Search

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun NavGraphBuilder.HomeGraph(
    navController: NavHostController
) {
    navigation(startDestination = BottomNavBarScreens.Home.route,route= GraphRoutes.BOTTOM_BAR_GRAPH.route){
        composable(route= BottomNavBarScreens.Home.route){
            Home(navController = navController)
        }

        composable(route= BottomNavBarScreens.Search.route){
            Search(navController = navController)
        }

        composable(route= BottomNavBarScreens.Library.route){
            Library(navController = navController)
        }

        composable(
            route= Screens.Detail.route,
            arguments = listOf(
                navArgument("id"){
                type= NavType.StringType },
                navArgument("type"){
                type= NavType.StringType }
            )
        ){
            val id=it.arguments?.getString("id")
            val type=it.arguments?.getString("type")?.uppercase()

            Detail(navController,id.toString(),type.toString())
        }

    }
}