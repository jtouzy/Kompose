package com.jtouzy.demo.app.ui

import androidx.compose.Model
import com.jtouzy.demo.ui.model.CatFact

@Model
object ScreenProvider {

    var currentScreen: Screen = Screen.Loading
        private set
    private val screenStack: MutableList<Screen> = mutableListOf()

    fun navigateTo(destination: Screen, addToStack: Boolean = false) {
        if (addToStack) screenStack.add(destination)
        currentScreen = destination
    }

    fun popBackStack() {
        if (screenStack.isEmpty()) return
        currentScreen = if (screenStack.size > 1 && screenStack.last() == currentScreen) {
            val index = screenStack.lastIndex
            screenStack.removeAt(index)
            screenStack[index - 1]
        } else {
            screenStack[screenStack.lastIndex]
        }
    }
}

sealed class Screen {
    object Loading : Screen()
    data class Home(val facts: List<CatFact>) : Screen()
    data class Details(val fact: CatFact) : Screen()
}
