package com.example.lonelyistheknight.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.lonelyistheknight.data.datastore.PreferencesKeys.LAST_SOLUTION_KEY
import com.example.lonelyistheknight.data.datastore.PreferencesKeys.SIZE_KEY
import com.example.lonelyistheknight.data.model.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(
    name = "settings",
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(
                context = context,
                sharedPreferencesName = "knight_prefs"
            )
        )
    }
)

class DatastoreManager(
    private val context: Context
) {

    val dataStore: DataStore<Preferences>
        get() = context.datastore

    suspend fun saveSolution(paths: List<List<Position>>) {
        val json = Gson().toJson(paths)
        dataStore.edit { preferences ->
            preferences[LAST_SOLUTION_KEY] = json
        }
    }

    fun getSavedSolution(): Flow<List<List<Position>>> {
        return dataStore.data.map { preferences ->
            val json = preferences[LAST_SOLUTION_KEY] ?: return@map emptyList()
            val type = object : TypeToken<List<List<Position>>>() {}.type
            Gson().fromJson(json, type)
        }
    }

    suspend fun clearSolution() {
        dataStore.edit { preferences ->
            preferences.remove(LAST_SOLUTION_KEY)
        }
    }

    suspend fun saveSolutionBoardSize(size: Int) {
        dataStore.edit { preferences ->
            preferences[SIZE_KEY] = size
        }
    }

    fun getSolutionBoardSize(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[SIZE_KEY] ?: -1
        }
    }

    suspend fun clearSolutionBoardSize() {
        dataStore.edit { preferences ->
            preferences.remove(SIZE_KEY)
        }
    }
}
