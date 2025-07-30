package com.example.lonelyistheknight.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lonelyistheknight.R
import com.example.lonelyistheknight.viewmodel.TileStateViewModel

@Composable
fun ResetButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.reset)
        )
    }
}