package com.example.lonelyistheknight.util

import com.example.lonelyistheknight.data.model.Position

fun Position.toChessNotation(size: Int): String {
    val alphabet = ('A'..'Z').toList()

    val columnLetter = (alphabet[column])
    val rowNumber = (size - row).toString()

    return "$columnLetter$rowNumber"
}