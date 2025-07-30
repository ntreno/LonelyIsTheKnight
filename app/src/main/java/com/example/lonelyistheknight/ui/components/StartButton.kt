package com.example.lonelyistheknight.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.lonelyistheknight.R

@Composable
fun StartButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick
    ) {
        Text(
            text = stringResource(R.string.start)
        )
    }
}