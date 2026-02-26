package com.example.lonelyistheknight

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lonelyistheknight.navigation.ChessNavGraph
import com.example.lonelyistheknight.navigation.Screen
import com.example.lonelyistheknight.ui.components.ChessAppBar
import com.example.lonelyistheknight.ui.components.ChessBottomNavBar
import com.example.lonelyistheknight.util.isAtTabRoot

@Composable
fun ChessApp() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route
    val currentScreen = when {
        route == Screen.Start.route -> Screen.Start
        route?.startsWith(Screen.Board.route) == true -> Screen.Board
        route == Screen.Settings.route -> Screen.Settings
        else -> Screen.Start
    }

    Scaffold(
        topBar = {
            ChessAppBar(
                currentScreen = currentScreen,
                canNavigateBack = backStackEntry?.destination?.isAtTabRoot() == false,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            ChessBottomNavBar(
                navController = navController
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
