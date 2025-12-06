package com.example.lonelyistheknight.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lonelyistheknight.R

@Composable
fun Instructions(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.instructions_title),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        Text(
            text = stringResource(R.string.choose_knight_position),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        Text(
            text = stringResource(R.string.choose_destination_position),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        Text(
            text = stringResource(R.string.result_display_description),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        Text(
            text = stringResource(R.string.press_reset),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
    }
}
