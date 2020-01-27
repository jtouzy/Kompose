package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Surface
import com.jtouzy.demo.app.ui.home.HomeScreen
import com.jtouzy.demo.app.ui.home.LoadingScreen
import com.jtouzy.demo.ui.characters.MarvelCharactersPresenter
import org.koin.android.ext.android.inject

class MarvelCharactersActivity : AppCompatActivity() {

    private val presenter by inject<MarvelCharactersPresenter>()
    private val screenProvider by inject<ScreenProvider>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MarvelApp() }
        presenter.loadCharacters()
    }

    @Composable
    fun MarvelApp() {
        MaterialTheme(
            colors = ColorPalette(primary = Color(0xFF, 0x00, 0x00))
        ) {
            Column {
                TopAppBar(title = { Text("Marvel Characters") })
                AppContent()
            }
        }
    }

    @Composable
    private fun AppContent() {
        Crossfade(screenProvider.screen) { screen ->
            Surface(color = (+MaterialTheme.colors()).background) {
                when (screen) {
                    Screen.Loading -> LoadingScreen()
                    is Screen.Home -> HomeScreen(screen.characters)
                    is Screen.Details -> TODO()
                }
            }
        }
    }
}
