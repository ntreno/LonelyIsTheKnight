package com.example.lonelyistheknight.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lonelyistheknight.R
import com.example.lonelyistheknight.data.model.Position
import com.example.lonelyistheknight.util.Constants
import com.example.lonelyistheknight.util.createBoard
import com.example.lonelyistheknight.viewmodel.TileStateViewModel

@Composable
fun ChessGrid(
    boardSize: Int,
    maxMoves: Int,
    knight: Position?,
    destination: Position?,
    currentPosition: Position?,
    viewModel: TileStateViewModel,
    paddingValues: PaddingValues
) {
    val boardTileCount = boardSize + 1 // boardSize (NxN) + extra row/column for labels

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val orientation = LocalConfiguration.current.orientation
    val padding = paddingValues.calculateTopPadding()

    val boardSizeDp = when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> screenWidth - padding
        Configuration.ORIENTATION_LANDSCAPE -> screenHeight - padding
        else -> screenWidth - padding
    }
    val tileSize = boardSizeDp / boardTileCount

    val board = remember(boardSize) { createBoard(boardSize) }

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            Spacer(modifier = Modifier.size(tileSize))

            val alphabet = ('A'..'Z').toList()

            for (column in 0 until boardSize) {
                Box(
                    modifier = Modifier
                        .size(tileSize)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = alphabet[column].toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        Row {
            Column {
                for (row in 0 until boardSize) {
                    Box(
                        modifier = Modifier
                            .size(tileSize)
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (boardSize - row).toString(),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            Column {
                board.forEach { row ->
                    Row {
                        row.forEach { (rowIndex, columnIndex) ->
                            val position = Position(rowIndex, columnIndex)
                            val isWhite = (rowIndex + columnIndex) % 2 == 0
                            val isKnight = knight == position
                            val isDestination = destination == position
                            val isCurrent = currentPosition == position

                            Box(
                                modifier = Modifier
                                    .size(tileSize)
                                    .background(
                                        when {
                                            isCurrent -> colorResource(R.color.chess_tile_in_path)
                                            isWhite -> colorResource(R.color.chess_tile_light)
                                            else -> colorResource(R.color.chess_tile_dark)
                                        }
                                    )
                                    .clickable(
                                        enabled = destination == null
                                    ) {
                                        viewModel.onTileClicked(
                                            tile = position,
                                            boardSize = boardSize,
                                            maxMoves = maxMoves
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isKnight) {
                                    Text(
                                        text = Constants.Symbols.KNIGHT, // ♘
                                        fontSize = tileSize.value.sp * 0.6f,
                                        color = MaterialTheme.colorScheme.scrim
                                    )
                                } else if (isDestination) {
                                    Text(
                                        text = Constants.Symbols.DESTINATION, // x
                                        fontSize = tileSize.value.sp * 0.6f,
                                        color = MaterialTheme.colorScheme.scrim
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
