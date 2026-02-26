package com.example.lonelyistheknight.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lonelyistheknight.ui.screens.ChessBoardScreen
import com.example.lonelyistheknight.ui.screens.UserInputScreen
import com.example.lonelyistheknight.util.Constants

@Composable
fun ChessNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Graph.Home.route,
        modifier = modifier
    ) {
        navigation (
            route = Graph.Home.route,
            startDestination = Graph.Home.startDestination
        ){
            composable(route = Screen.Start.route) {
                UserInputScreen(navController = navController)
            }

            composable(route = "${Screen.Board.route}/{size}/{moves}") { backStackEntry ->
                val size = backStackEntry.arguments?.getString("size")?.toIntOrNull()
                    ?: Constants.DEFAULT_BOARD_SIZE
                val moves = backStackEntry.arguments?.getString("moves")?.toIntOrNull()
                    ?: Constants.DEFAULT_MAX_MOVES
                ChessBoardScreen(
                    size = size,
                    maxMoves = moves
                )
            }
        }
    }
}
