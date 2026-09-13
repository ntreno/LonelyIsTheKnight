package com.example.lonelyistheknight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lonelyistheknight.R
import com.example.lonelyistheknight.data.datastore.DatastoreManager
import com.example.lonelyistheknight.navigation.Screen
import com.example.lonelyistheknight.ui.components.LastSolutionsFAB
import com.example.lonelyistheknight.ui.components.ResultsAlertDialog
import com.example.lonelyistheknight.ui.components.StartButton
import com.example.lonelyistheknight.util.Constants

@Composable
fun UserInputScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val datastore = remember { DatastoreManager(context) }

    var sizeInput by rememberSaveable { mutableStateOf("") }
    var maxMovesInput by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(false) }

    var showDialog by rememberSaveable { mutableStateOf(false) }
    val lastSolutionPaths by datastore.getSavedSolution().collectAsState(emptyList())
    val lastSize by datastore.getSolutionBoardSize().collectAsState(-1)

    Scaffold(
        floatingActionButton = {
            LastSolutionsFAB(onClick = { showDialog = true })
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(
                    R.string.please_enter,
                    Constants.MIN_BOARD_SIZE,
                    Constants.MAX_BOARD_SIZE,
                    Constants.MAX_MOVES_LOWER_LIMIT,
                    Constants.MAX_MOVES_HIGHER_LIMIT
                ),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = sizeInput,
                onValueChange = {
                    sizeInput = it
                    error = false
                },
                isError = error,
                label = {
                    Text(
                        text = stringResource(R.string.board_size)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = maxMovesInput,
                onValueChange = {
                    maxMovesInput = it
                    error = false
                },
                isError = error,
                label = {
                    Text(
                        text = stringResource(R.string.max_moves)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            StartButton(onClick = {
                val size = sizeInput.toIntOrNull()
                val moves = maxMovesInput.toIntOrNull()
                if (size in Constants.MIN_BOARD_SIZE..Constants.MAX_BOARD_SIZE &&
                    moves in Constants.MAX_MOVES_LOWER_LIMIT..Constants.MAX_MOVES_HIGHER_LIMIT
                ) {
                    navController.navigate("${Screen.Board.route}/$size/$moves")
                } else {
                    error = true
                }
            })

            if (showDialog) {
                ResultsAlertDialog(
                    lastSolutionPaths = lastSolutionPaths,
                    lastSize = lastSize,
                    onDismissRequest = { showDialog = false },
                    onClick = { showDialog = false }
                )
            }
        }
    }
}
