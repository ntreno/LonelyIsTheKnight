package com.example.lonelyistheknight.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lonelyistheknight.R

@Composable
fun LastSolutionsFAB(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = stringResource(R.string.show_last_solution)
        )
    }
}