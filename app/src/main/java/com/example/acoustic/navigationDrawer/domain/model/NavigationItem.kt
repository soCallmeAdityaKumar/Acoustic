package com.example.acoustic.navigationDrawer.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.acoustic.R

data class NavigationItem(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount:Int?=null,
    val route:String,
)

@Composable
fun navigationDrawerItem():List<NavigationItem>{
    return listOf(
        NavigationItem(
            "All",
            ImageVector.vectorResource(id = R.drawable.house_solid),
            ImageVector.vectorResource(id = R.drawable.house_solid),
            route = "all_screen"
        ),
        NavigationItem(
            "Profile",
            ImageVector.vectorResource(id = R.drawable.user_solid),
            ImageVector.vectorResource(id = R.drawable.user_solid),
            route = "profile_screen"
        ),
        NavigationItem(
            "Settings",
            ImageVector.vectorResource(id = R.drawable.gear_solid),
            ImageVector.vectorResource(id = R.drawable.gear_solid),
            route = "settings_screen"
        ),
        NavigationItem(
            "Logout",
            Icons.Filled.CheckCircle,
            Icons.Outlined.CheckCircle,
            route = "all_screen"
        ),
    )
}

