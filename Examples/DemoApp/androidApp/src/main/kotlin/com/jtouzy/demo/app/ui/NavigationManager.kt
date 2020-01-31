package com.jtouzy.demo.app.ui

import androidx.compose.Model
import com.jtouzy.demo.ui.model.Character

@Model
class NavigationManager {

    var currentScreen: Screen = Screen.Home
        private set
    private val screenStack = mutableListOf(currentScreen)

    fun navigateTo(screen: Screen) {
        screenStack += screen
        currentScreen = screen
    }

    fun pop(): Boolean =
        if (screenStack.size > 1) {
            screenStack.removeAt(screenStack.lastIndex)
            navigateTo(screenStack.last())
            true
        } else {
            false
        }
}

sealed class Screen {
    object Home : Screen()
    data class Quote(val character: Character) : Screen()
}
