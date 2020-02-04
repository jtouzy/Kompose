package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.jtouzy.demo.app.ui.characters.CharactersScreen
import com.jtouzy.demo.app.ui.characters.CharactersViewModel
import com.jtouzy.demo.app.ui.common.themeColors
import com.jtouzy.demo.app.ui.quote.QuoteScreen
import com.jtouzy.demo.app.ui.quote.QuoteViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class KomposeActivity : AppCompatActivity() {

    private val navigationManager by inject<NavigationManager>()
    private val charactersViewModel by viewModel<CharactersViewModel>()
    private val quoteViewModel by viewModel<QuoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { KomposeApp() }
    }

    override fun onBackPressed() {
        if (!navigationManager.pop()) super.onBackPressed()
    }

    @Composable
    private fun KomposeApp() {
        MaterialTheme(colors = themeColors) {
            when (val screen = navigationManager.currentScreen) {
                Screen.Home -> CharactersScreen(navigationManager, charactersViewModel).MainScreen()
                is Screen.Quote -> QuoteScreen(navigationManager, quoteViewModel).MainScreen(screen.character)
            }
        }
    }
}
