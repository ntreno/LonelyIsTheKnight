package com.example.lonelyistheknight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lonelyistheknight.R
import com.example.lonelyistheknight.data.sharedPref.SharedPrefsManager
import com.example.lonelyistheknight.navigation.Route
import com.example.lonelyistheknight.ui.components.*
import com.example.lonelyistheknight.util.Constants

@Composable
fun UserInputScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val sharedPrefs = remember { SharedPrefsManager(context) }

    var sizeInput by rememberSaveable { mutableStateOf("") }
    var maxMovesInput by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    val lastSolutionPaths = sharedPrefs.getSavedSolution()
    val lastsize = sharedPrefs.getSolutionBoardSize()

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
                if (size in Constants.MIN_BOARD_SIZE..Constants.MAX_BOARD_SIZE && moves in Constants.MAX_MOVES_LOWER_LIMIT..Constants.MAX_MOVES_HIGHER_LIMIT) {
                    navController.navigate("${Route.Board}/$size/$moves")
                } else {
                    error = true
                }
            })

            if (showDialog) {
                ResultsAlertDialog(
                    lastSolutionPaths = lastSolutionPaths,
                    lastSize = lastsize,
                    onDismissRequest = { showDialog = false },
                    onClick = { showDialog = false }
                )
            }
        }
    }
}