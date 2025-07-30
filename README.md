# ♘ Lonely Is The Knight

**A sleek Android chessboard visualizer for knight moves and paths**  
_Using modern Jetpack Compose, MVVM, and a splash of animated logic!_

---

- Gradle Version: 8.10.2
- AGP: 8.8.1

---

## 📱 Features

- 🧠 Tap to set the knight's start and destination tiles
- 🧭 Visualize all possible knight paths between two tiles
- 🔁 Animates knight movement along each path in a loop
- 🔢 Supports custom board sizes from 6x6 to 16x16
- 🔂 Optional: Limit max moves for path solutions
- ♟️ Real chess notation for tiles (`A1`, `E4`, etc.)
- 📋 Lists all solutions as text below the board
- 🔄 Reset anytime to start fresh
- 🌗 Dark/light theme support via Material 3
- 🧪 Clean architecture with full separation of concerns
- 📲 Adaptive UI for screen rotation
- 📦 Shared Preferences to store last solution

---

## 🧱 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM architecture**
- **StateFlow + ViewModel**
- **Coroutines**
- **Navigation Component**
- **Custom animations with `LaunchedEffect`**
- **Adaptive launcher icon**
- **Responsive layout (portrait & landscape)**
- **Material 3 (Material You) — for theming and styling**
- **Custom Theme — UI theme (colors, typography, shapes) generated using Material Theme Builder: https://material-foundation.github.io/material-theme-builder/**

---

## 🧭 Project Structure

📁 com.example.lonelyistheknight
- data
    - model
        - Position.kt
    - sharedPref
        - SharedPrefsManager.kt
- navigation
    - ChessNavGraph.kt
    -Route.kt
- ui
    - components         # Reusable UI pieces (e.g. buttons, grids)
    - screens            # SelectSizeScreen, ChessBoardScreen
    - theme              # MaterialTheme, colors, typography
- util
    - BoardGenerator.kt
    - Constants.kt
    - Extensions.kt
    - PathFinder.kt
- viewmodel
    - TileStateViewModel.kt
    - TileStateViewModelFactory.kt
- ChessApp.kt
- MainActivity.kt
- res
    - drawable/          # Backgrounds
    - font/              # Project Fonts
    - mipmap/            # Launcher icon
    - values/            # Strings, colors, themes

_Note: Documentation was drafted and refined with AI assistance for clarity and structure_
