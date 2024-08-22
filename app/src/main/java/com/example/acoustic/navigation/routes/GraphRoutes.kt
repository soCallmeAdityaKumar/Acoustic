package com.example.acoustic.navigation.routes

sealed class GraphRoutes(val route:String){
    data object AUTHENTICATION_GRAPH: GraphRoutes("auth_graph")
    data object ROOT_GRAPH: GraphRoutes("root_graph")
    data object  HOME_GRAPH: GraphRoutes("home_graph")
    data object HOME_NAVIGATION: GraphRoutes("home_navigations")
    data object NAVIGATION_DRAWER_GRAPH: GraphRoutes("navigation_drawer_graph")
    data object BOTTOM_BAR_GRAPH: GraphRoutes("bottom_bar_graph")

}