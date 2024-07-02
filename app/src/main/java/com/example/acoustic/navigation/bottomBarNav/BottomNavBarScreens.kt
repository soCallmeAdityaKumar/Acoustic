package com.example.acoustic.navigation.bottomBarNav

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBarScreens(
    val title:String,
    val route:String,
    val icon: ImageVector
) {
    data object Home:BottomNavBarScreens(
        title = "Home",
        route = "home_bottom",
        icon = Icons.Default.Home
    )
    data object Search:BottomNavBarScreens(
        title = "Search",
        route = "search_bottom",
        icon = Icons.Default.Search
    )
    data object Library:BottomNavBarScreens(
        title = "Library",
        route = "library_bottom",
        icon = Icons.Default.List
    )
}