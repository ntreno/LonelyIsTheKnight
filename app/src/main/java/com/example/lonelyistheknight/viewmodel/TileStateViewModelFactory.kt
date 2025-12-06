package com.example.lonelyistheknight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lonelyistheknight.data.sharedPref.SharedPrefsManager

class TileStateViewModelFactory(
    private val sharedPrefManager: SharedPrefsManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TileStateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TileStateViewModel(sharedPrefManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
