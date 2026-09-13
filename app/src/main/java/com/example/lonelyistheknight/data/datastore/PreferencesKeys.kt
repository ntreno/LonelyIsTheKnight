package com.example.lonelyistheknight.data.datastore

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val LAST_SOLUTION_KEY = stringPreferencesKey("last_solution")
    val SIZE_KEY = intPreferencesKey("size")
}