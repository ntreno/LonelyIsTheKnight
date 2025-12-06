package com.example.lonelyistheknight.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lonelyistheknight.ui.screens.ChessBoardScreen
import com.example.lonelyistheknight.ui.screens.UserInputScreen
import com.example.lonelyistheknight.util.Constants

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
            val size = backStackEntry.arguments?.getString("size")?.toIntOrNull()
                ?: Constants.DEFAULT_BOARD_SIZE
            val moves = backStackEntry.arguments?.getString("moves")?.toIntOrNull()
                ?: Constants.DEFAULT_MAX_MOVES
            ChessBoardScreen(
                size = size,
                maxMoves = moves,
                paddingValues = contentPaddingValues
            )
        }
    }
}
