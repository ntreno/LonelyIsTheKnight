package com.example.lonelyistheknight.navigation

import androidx.annotation.StringRes
import com.example.lonelyistheknight.R

sealed class Screen(
    override val route: String,
    @StringRes val title: Int
) : AppRoute {
    object Start : Screen(
        route = "home",
        title = R.string.home
    )

    object Board : Screen(
        route = "board",
        title = R.string.board
    )

    object Settings : Screen(
        route = "settings",
        title = R.string.settings
    )
}
