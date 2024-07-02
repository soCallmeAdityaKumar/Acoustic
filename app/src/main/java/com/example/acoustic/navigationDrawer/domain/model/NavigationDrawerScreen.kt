package com.example.acoustic.navigationDrawer.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationDrawerScreen(
    val title:String,
    val route:String,
) {
    data object Profile:NavigationDrawerScreen(
        title = "Profile",
        route = "profile_screen",
    )
    data object Settings:NavigationDrawerScreen(
        title = "Settings",
        route = "settings_screen",
    )
    data object Library:NavigationDrawerScreen(
        title = "Library",
        route = "library_screen",
    )
}