package com.example.lonelyistheknight.util

object Constants {
    const val MIN_BOARD_SIZE = 6
    const val MAX_BOARD_SIZE = 16
    const val DEFAULT_BOARD_SIZE = 8

    const val MAX_MOVES_LOWER_LIMIT = 1
    const val MAX_MOVES_HIGHER_LIMIT = 6
    const val DEFAULT_MAX_MOVES = 3

    const val ANIMATION_DELAY_MS = 500L
    const val ANIMATION_PAUSE_MS = 300L

    object Symbols {
        const val KNIGHT = "\u2658"
        const val DESTINATION = "\u0078"
        const val SEPARATOR = " → "
    }
}
