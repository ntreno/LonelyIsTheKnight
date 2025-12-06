package com.example.lonelyistheknight.navigation

import androidx.annotation.StringRes
import com.example.lonelyistheknight.R

enum class Route(@StringRes val title: Int) {
    Start(R.string.app_name),
    Board(R.string.board)
}
