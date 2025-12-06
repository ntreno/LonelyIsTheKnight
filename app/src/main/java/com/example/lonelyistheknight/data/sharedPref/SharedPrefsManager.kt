package com.example.lonelyistheknight.data.sharedPref

import android.content.Context
import com.example.lonelyistheknight.data.model.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsManager(context: Context) {

    private val prefs = context.getSharedPreferences("knight_prefs", Context.MODE_PRIVATE)

    fun saveSolution(paths: List<List<Position>>) {
        val json = Gson().toJson(paths)
        prefs.edit().putString("last_solution", json).apply()
    }

    fun getSavedSolution(): List<List<Position>> {
        val json = prefs.getString("last_solution", null) ?: return emptyList()
        val type = object : TypeToken<List<List<Position>>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun clearSolution() {
        prefs.edit().remove("last_solution").apply()
    }

    fun saveSolutionBoardSize(size: Int) {
        prefs.edit()
            .putInt("size", size)
            .apply()
    }

    fun getSolutionBoardSize(): Int {
        val size = prefs.getInt("size", -1)
        return size
    }

    fun clearSolutionBoardSize() {
        prefs.edit().remove("size").apply()
    }
}
