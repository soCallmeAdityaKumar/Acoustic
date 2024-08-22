package com.example.acoustic.navigation.graph

import android.app.Activity
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.acoustic.login.presentation.LoginScreen
import com.example.acoustic.navigation.routes.GraphRoutes
import com.example.acoustic.singup.Register

fun NavGraphBuilder.AuthGraph(navController: NavHostController,activity:Activity){
    navigation( startDestination = AuthRoute.Login.route, route = GraphRoutes.AUTHENTICATION_GRAPH.route) {
        composable(route = AuthRoute.Login.route){
            LoginScreen(navController, activity)
        }
        composable(route = AuthRoute.Register.route){
            Register(navController)
        }
    }

}

sealed class AuthRoute(val route:String){
    data object Login: AuthRoute("login_route")
    data object Register: AuthRoute("register_route")

}