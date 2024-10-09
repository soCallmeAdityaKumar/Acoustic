package com.example.acoustic.navigation.bottomBarNav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.acoustic.navigation.routes.BottomNavBarScreens
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.albumBlackBackground
import com.example.acoustic.ui.theme.loginButtonColor

@Composable
fun BottomBar(navController: NavHostController){

    val screens= listOf(
        BottomNavBarScreens.Home,
        BottomNavBarScreens.Search,
        BottomNavBarScreens.Library
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination= navBackStackEntry?.destination
    var selectedScreens=screens[0]

        BottomNavigation(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 50.dp)
                .clip(RoundedCornerShape(20.dp)),
            backgroundColor = loginButtonColor
        ) {
            screens.forEach{screen->
                AddItem(screen,currentDestination,navController,selectedScreens){
                    selectedScreens=it
                }
            }
        }

}

@Composable
fun RowScope.AddItem(
    screen: BottomNavBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController,
    rememberSelectedScreen: BottomNavBarScreens,
    changeSelectedScreen:(BottomNavBarScreens)->Unit
){
//    var rememberSelectedScreen by remember {
//        mutableStateOf(screen)
//    }
    BottomNavigationItem(
        label={
            Text(
                text = screen.title,
                fontSize =MaterialTheme.typography.body1.fontSize,
                style = NavigationRowText.bodyLarge,
                color = if(currentDestination?.route==screen.route) Color.White else Color.Black
            )
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription ="bottom_nav_icons", tint = if(currentDestination?.route==screen.route)Color.White else Color.Black)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route==screen.route
        }==true,
        onClick = {
            changeSelectedScreen(screen)
            navController.navigate(screen.route)
        }
    )
}