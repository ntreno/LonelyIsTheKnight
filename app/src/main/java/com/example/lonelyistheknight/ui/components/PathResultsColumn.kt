package com.example.lonelyistheknight.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import com.example.lonelyistheknight.R
import com.example.lonelyistheknight.data.model.Position
import com.example.lonelyistheknight.util.*

@Composable
fun PathResultsColumn(
    size: Int,
    paths: List<List<Position>>,
    knight: Position?,
    destination: Position?,
    isComputing: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        when {
            knight == null || destination == null -> {
                Text(text = "")
            }

            isComputing -> {
                Text(text = stringResource(R.string.computing_paths))
            }

            paths.isEmpty() -> {
                Text(text = stringResource(R.string.no_paths))
            }

            else -> {
                LazyColumn {
                    items(paths.size) { index ->
                        val path = paths[index]
                        Text(
                            text = stringResource(
                                R.string.path_display,
                                index + 1,
                                path.joinToString(Constants.Symbols.SEPARATOR) { it.toChessNotation(
                                    size
                                ) }
                            ),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}