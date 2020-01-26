package com.jtouzy.demo.app.ui

import androidx.compose.Model
import com.jtouzy.demo.ui.model.CatFact

@Model
data class ScreenProvider(var screen: Screen = Screen.Loading) {

    fun goToDetails(fact: CatFact) {
        screen = Screen.Details(fact)
    }
}

sealed class Screen {
    object Loading : Screen()
    data class Home(val facts: List<CatFact>) : Screen()
    data class Details(val fact: CatFact) : Screen()
}
