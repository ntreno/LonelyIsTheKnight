package com.example.lonelyistheknight

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lonelyistheknight.navigation.ChessNavGraph
import com.example.lonelyistheknight.navigation.Route
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
        topBar = {
            ChessAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        ChessNavGraph(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}
