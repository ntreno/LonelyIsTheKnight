package com.example.lonelyistheknight.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lonelyistheknight.data.sharedPref.SharedPrefsManager
import com.example.lonelyistheknight.ui.components.ChessGrid
import com.example.lonelyistheknight.ui.components.Instructions
import com.example.lonelyistheknight.ui.components.PathResultsColumn
import com.example.lonelyistheknight.ui.components.ResetButton
import com.example.lonelyistheknight.util.Constants
import com.example.lonelyistheknight.viewmodel.TileStateViewModel
import com.example.lonelyistheknight.viewmodel.TileStateViewModelFactory

@Composable
fun ChessBoardScreen(
    size: Int,
    maxMoves: Int
) {
    val context = LocalContext.current
    val sharedPrefs = remember { SharedPrefsManager(context) }
    val viewModel: TileStateViewModel = viewModel(
        factory = TileStateViewModelFactory(sharedPrefs)
    )

    val knight by viewModel.knightPosition.collectAsState()
    val destination by viewModel.destination.collectAsState()
    val current by viewModel.currentAnimatedPosition.collectAsState() // intermediate positions to solution path
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
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
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
                viewModel = viewModel
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
fun ChessBoardScreenPreview() {
    ChessBoardScreen(
        Constants.DEFAULT_BOARD_SIZE,
        Constants.DEFAULT_MAX_MOVES
    )
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun ChessBoardScreenLandscapePreview() {
    ChessBoardScreen(
        Constants.DEFAULT_BOARD_SIZE,
        Constants.DEFAULT_MAX_MOVES
    )
}
