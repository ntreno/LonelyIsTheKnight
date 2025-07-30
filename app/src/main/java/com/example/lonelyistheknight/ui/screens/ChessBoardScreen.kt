package com.example.lonelyistheknight.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lonelyistheknight.data.sharedPref.SharedPrefsManager
import com.example.lonelyistheknight.ui.components.*
import com.example.lonelyistheknight.util.Constants
import com.example.lonelyistheknight.viewmodel.*

@Composable
fun ChessBoardScreen(
    size: Int,
    maxMoves: Int,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val sharedPrefs = remember { SharedPrefsManager(context) }
    val viewModel: TileStateViewModel = viewModel(
        factory = TileStateViewModelFactory(sharedPrefs)
    )

    val knight by viewModel.knightPosition.collectAsState()
    val destination by viewModel.destination.collectAsState()
    val current by viewModel.currentAnimatedPosition.collectAsState() //this symbolizes intermediate positions to solution path
    val paths by viewModel.paths.collectAsState()
    val isComputing by viewModel.isComputing.collectAsState()

    val orientation = LocalConfiguration.current.orientation
    val padding = 16.dp

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = padding),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Instructions()
            Spacer(modifier = Modifier.weight(1f))
            ChessGrid(
                boardSize = size,
                maxMoves = maxMoves,
                knight = knight,
                destination = destination,
                currentPosition = current,
                viewModel = viewModel,
                paddingValues = paddingValues
            )
            Spacer(modifier = Modifier.weight(1f))
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PathResultsColumn(
                    size = size,
                    paths = paths,
                    knight = knight,
                    destination = destination,
                    isComputing = isComputing,
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
                ResetButton(onClick = { viewModel.reset() })
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = padding),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Instructions()
            Spacer(modifier = Modifier.weight(1f))
            ChessGrid(
                boardSize = size,
                maxMoves = maxMoves,
                knight = knight,
                destination = destination,
                currentPosition = current,
                viewModel = viewModel,
                paddingValues = paddingValues
            )
            Spacer(modifier = Modifier.weight(1f))
            PathResultsColumn(
                size = size,
                paths = paths,
                knight = knight,
                destination = destination,
                isComputing = isComputing,
                modifier = Modifier
                    .weight(5f)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            ResetButton(onClick = { viewModel.reset() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChessBoardScreenPreview(){
    ChessBoardScreen(
        Constants.DEFAULT_BOARD_SIZE,
        Constants.DEFAULT_MAX_MOVES,
        PaddingValues(16.dp)
    )
}

@Preview(showBackground = true, widthDp = 800, heightDp = 300)
@Composable
fun ChessBoardScreenLandscapePreview(){
    ChessBoardScreen(
        Constants.DEFAULT_BOARD_SIZE,
        Constants.DEFAULT_MAX_MOVES,
        PaddingValues(16.dp)
    )
}
