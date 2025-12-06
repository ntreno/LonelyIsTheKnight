package com.example.lonelyistheknight.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lonelyistheknight.R
import com.example.lonelyistheknight.data.model.Position
import com.example.lonelyistheknight.util.Constants
import com.example.lonelyistheknight.util.toChessNotation

@Composable
fun ResultsAlertDialog(
    lastSolutionPaths: List<List<Position>>,
    lastSize: Int,
    onDismissRequest: () -> Unit,
    onClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(R.string.last_solution_title))
        },
        text = {
            if (lastSolutionPaths.isNotEmpty()) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    lastSolutionPaths.forEachIndexed { index, path ->
                        Text(
                            text = stringResource(
                                R.string.path_display,
                                index + 1,
                                path.joinToString(Constants.Symbols.SEPARATOR) {
                                    it.toChessNotation(
                                        lastSize
                                    )
                                }
                            ),
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            } else {
                Text(text = stringResource(R.string.no_saved_solution))
            }
        },
        confirmButton = {
            Button(onClick = onClick) {
                Text(stringResource(R.string.close))
            }
        }
    )
}
