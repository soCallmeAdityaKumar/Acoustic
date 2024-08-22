package com.example.acoustic.navigation.routes

sealed class NavigationDrawerScreen(
    val title:String,
    val route:String,
) {
    data object All: NavigationDrawerScreen(
        title = "All",
        route = "all_screen",
    )
    data object Profile: NavigationDrawerScreen(
        title = "Profile",
        route = "profile_screen",
    )
    data object Settings: NavigationDrawerScreen(
        title = "Settings",
        route = "settings_screen",
    )
}