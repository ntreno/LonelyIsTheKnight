package com.example.lonelyistheknight.util

import android.util.Log
import com.example.lonelyistheknight.data.model.Position

private const val TAG = "PathFinder"

fun getKnightMoves(pos: Position, size: Int): List<Position> {
    val moves = listOf(
        Position(pos.row + 2, pos.column + 1),
        Position(pos.row + 2, pos.column - 1),
        Position(pos.row - 2, pos.column + 1),
        Position(pos.row - 2, pos.column - 1),
        Position(pos.row + 1, pos.column + 2),
        Position(pos.row + 1, pos.column - 2),
        Position(pos.row - 1, pos.column + 2),
        Position(pos.row - 1, pos.column - 2)
    )
    return moves.filter {
        it.row in 0 until size && it.column in 0 until size
    }
}

fun findAllKnightPaths(
    start: Position,
    end: Position,
    size: Int,
    maxDepth: Int
): List<List<Position>> {
    Log.d(TAG, "Computing paths...")

    val results = mutableListOf<List<Position>>()

    fun dfs(current: Position, path: MutableList<Position>, visited: MutableSet<Position>) {

        if (path.size > maxDepth + 1) return

        if (current == end) {
            results.add(path.toList())
            return
        }

        for (next in getKnightMoves(current, size)) {
            if (next !in visited) {
                visited.add(next)
                path.add(next)
                dfs(next, path, visited)
                path.removeAt(path.lastIndex)
                visited.remove(next)
            }
        }
    }

    dfs(start, mutableListOf(start), mutableSetOf(start))
    Log.d(TAG, "Found ${results.size} solutions")
    return results
}

