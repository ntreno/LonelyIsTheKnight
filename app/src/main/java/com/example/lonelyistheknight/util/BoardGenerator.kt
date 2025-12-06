package com.example.lonelyistheknight.util

import com.example.lonelyistheknight.data.model.Position

fun createBoard(size: Int): List<List<Position>> {
    return List(size) { row ->
        List(size) { column ->
            Position(row, column)
        }
    }
}
