package com.example.acoustic.navigation.routes

sealed class Screens(
    val title:String,
    val route:String
) {

    data object Detail:Screens(
        title = "Detail",
        route = "detail_screen/{id}/{type}"
    )
}