package com.example.lonelyistheknight.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.lonelyistheknight.ui.screens.*

@Composable
fun ChessNavGraph(
    navController: NavHostController,
    contentPaddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Route.Start.name,
        modifier = Modifier
            .padding(contentPaddingValues)
    ) {
        composable(route = Route.Start.name) {
            UserInputScreen(navController = navController)
        }

        composable(route = "${Route.Board}/{size}/{moves}") { backStackEntry ->
            val size = backStackEntry.arguments?.getString("size")?.toIntOrNull() ?: 8
            val moves = backStackEntry.arguments?.getString("moves")?.toIntOrNull() ?: 3
                ChessBoardScreen(
                    size = size,
                    maxMoves = moves,
                    paddingValues = contentPaddingValues
                )
        }
    }
}