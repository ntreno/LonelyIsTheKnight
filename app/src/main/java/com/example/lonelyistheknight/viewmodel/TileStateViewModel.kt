package com.example.lonelyistheknight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonelyistheknight.data.datastore.DatastoreManager
import com.example.lonelyistheknight.data.model.Position
import com.example.lonelyistheknight.util.Constants
import com.example.lonelyistheknight.util.findAllKnightPaths
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TileStateViewModel @Inject constructor(
    private val datastore: DatastoreManager
) : ViewModel() {
    private val _isComputing = MutableStateFlow(false)
    val isComputing: StateFlow<Boolean> = _isComputing.asStateFlow()

    private val _knightPosition = MutableStateFlow<Position?>(null)
    val knightPosition = _knightPosition.asStateFlow()

    private val _destination = MutableStateFlow<Position?>(null)
    val destination = _destination.asStateFlow()

    private val _paths = MutableStateFlow<List<List<Position>>>(emptyList())
    val paths = _paths.asStateFlow()

    private val _currentAnimatedPosition = MutableStateFlow<Position?>(null)
    val currentAnimatedPosition = _currentAnimatedPosition.asStateFlow()

    private val _isAnimating = MutableStateFlow(false)
    val isAnimating = _isAnimating.asStateFlow()

    private var animationJob: Job? = null

    fun onTileClicked(tile: Position, boardSize: Int, maxMoves: Int) {
        when {
            _knightPosition.value == null -> _knightPosition.value = tile
            _destination.value == null && tile != _knightPosition.value -> {
                _destination.value = tile
                computePaths(
                    boardSize = boardSize,
                    maxMoves = maxMoves
                )
            }

            else -> Unit
        }
    }

    private fun computePaths(boardSize: Int, maxMoves: Int) {
        val start = _knightPosition.value
        val end = _destination.value

        if (start != null && end != null) {
            _isComputing.value = true

            viewModelScope.launch {
                val result = withContext(Dispatchers.Default) {
                    findAllKnightPaths(
                        start = start,
                        end = end,
                        size = boardSize,
                        maxDepth = maxMoves
                    )
                }
                _paths.value = result
                _isComputing.value = false
                if (result.isNotEmpty()) {
                    updateDatastore(
                        result = result,
                        size = boardSize
                    )
                    startAnimation()
                }
            }
        }
    }

    private fun startAnimation() {
        animationJob?.cancel()
        animationJob = viewModelScope.launch {
            _isAnimating.value = true
            while (_isAnimating.value) {
                for (path in _paths.value) {
                    for (pos in path) {
                        ensureActive() // ensures active animation (reset not pressed)
                        _currentAnimatedPosition.value = pos
                        delay(Constants.ANIMATION_DELAY_MS)
                    }
                    ensureActive()
                    _currentAnimatedPosition.value = null
                    delay(Constants.ANIMATION_PAUSE_MS)
                }
            }
        }
    }

    private suspend fun updateDatastore(
        result: List<List<Position>>,
        size: Int
    ) {
        datastore.clearSolution()
        datastore.saveSolution(result)
        datastore.clearSolutionBoardSize()
        datastore.saveSolutionBoardSize(size)
    }

    fun reset() {
        viewModelScope.launch {
            animationJob?.cancelAndJoin() // finishes animation before resetting
            _isAnimating.value = false
            _knightPosition.value = null
            _destination.value = null
            _paths.value = emptyList()
            _currentAnimatedPosition.value = null
            _isComputing.value = false
        }
    }
}
