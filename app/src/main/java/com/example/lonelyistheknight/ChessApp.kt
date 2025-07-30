package com.example.lonelyistheknight

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.lonelyistheknight.navigation.*
import com.example.lonelyistheknight.ui.components.ChessAppBar

@Composable
fun ChessApp() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route
    val currentScreen = when {
        route == Route.Start.name -> Route.Start
        route?.startsWith(Route.Board.name) == true -> Route.Board
        else -> Route.Start
    }

    Scaffold(
        topBar =  {
            ChessAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        ChessNavGraph(
            navController,
            innerPadding
        )
    }
}
