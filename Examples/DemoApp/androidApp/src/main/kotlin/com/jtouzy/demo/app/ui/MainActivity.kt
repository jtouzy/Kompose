package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import com.jtouzy.demo.app.ui.characters.CharactersScreen
import com.jtouzy.demo.app.ui.quote.QuoteScreen
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.CharactersPresenter
import com.jtouzy.demo.ui.characters.CharactersViewState
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val presenter by inject<CharactersPresenter>()
    private val store by inject<Store<CharactersViewState>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { KomposeApp() }
        presenter.loadCharacters()
    }

    override fun onBackPressed() {
        if (!NavigationManager.popBackStack()) super.onBackPressed()
    }

    @Composable
    private fun KomposeApp() {
        MaterialTheme(colors = themeColors) {
            AppContent()
        }
    }

    @Composable
    private fun AppContent() {
        Crossfade(NavigationManager.currentScreen) { screen ->
            Surface(color = (+MaterialTheme.colors()).background) {
                when (screen) {
                    Screen.Home -> CharactersScreen(store)
                    Screen.Quote -> QuoteScreen()
                }
            }
        }
    }
}
