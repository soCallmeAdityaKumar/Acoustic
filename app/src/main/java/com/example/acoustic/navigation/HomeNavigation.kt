package com.example.acoustic.navigation

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.navigation.bottomBarNav.BottomBar
import com.example.acoustic.navigation.home.HomeGraph
import com.example.acoustic.navigationDrawer.domain.model.NavigationDrawerGraph
import com.example.acoustic.navigationDrawer.domain.model.NavigationDrawerScreen
import com.example.acoustic.navigationDrawer.domain.model.navigationDrawerItem
import com.example.acoustic.navigationDrawer.presentation.components.CustomNavigationDrawer
import com.example.acoustic.ui.theme.Acoustic
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.loginButtonColor
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigation(activity: Activity, homeNavController: NavHostController) {
    val navigationItem= navigationDrawerItem()
    val scope= rememberCoroutineScope()
    val navController= rememberNavController()
    var selectedNavigationItem by remember {
        mutableStateOf(navigationItem[0])
    }

    val bottomNavItems = listOf("home_bottom", "library_bottom", "search_bottom")
    val drawerNavItems = listOf("profile_screen", "settings_screen")

    var drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)

    var showBottomBar by remember { mutableStateOf(true) }
    var showDrawer by remember { mutableStateOf(true) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        showBottomBar = destination.route in bottomNavItems
        showDrawer = destination.route in drawerNavItems
    }


    ModalNavigationDrawer(
        drawerContent = {
            CustomNavigationDrawer(
            selectedNavigationItem =selectedNavigationItem,
            onNavigationItemClick ={
                selectedNavigationItem=it
                navController.navigate(it.route)
           },
            onCloseClick = { scope.launch { drawerState.close()} })
            {
                val sharedPref=SharedPref(activity.baseContext)
                if(sharedPref.ifContain("USER_TOKEN")){
                    sharedPref.delete("USER_TOKEN")
                    homeNavController.navigate(Screen.AUTHENTICATION_GRAPH.route){
                        popUpTo(Screen.ROOT_GRAPH.route)
                    }

                }
            }
        },
        drawerState=drawerState
    ) {
        Scaffold(
            topBar = {
                if(!showDrawer){
                    TopAppBar(
                        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = loginButtonColor, titleContentColor = Color.White),
                        title = { Text(text = "Hello !!", fontSize = Acoustic.headlineSmall.fontSize, style = NavigationRowText.bodyLarge) },
                        navigationIcon = {
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
                                onClick = {
                                    if(showDrawer){
                                        navController.navigate(Screen.HOME_GRAPH.route){
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop=true
                                        }
                                    }
                                    else{
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }

                                }) {
                                if(!showDrawer){
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }else{
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "ArrowBack"
                                    )
                                }


                            }
                        }
                    )
                }
            },
            bottomBar = { if (showBottomBar)BottomBar(navController = navController)},
        ) {
            Log.d(TAG,it.toString())
            HomeGraph(navController)
        }

    }

}

