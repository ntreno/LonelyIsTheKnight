package com.example.lonelyistheknight.navigation

sealed class Graph(
    override val route: String,
    val startDestination: String
) : AppRoute {

    object Home : Graph(
        route = "home_graph",
        startDestination = Screen.Start.route
    )

    object Settings : Graph(
        route = "settings_graph",
        startDestination = Screen.Settings.route
    )
}
