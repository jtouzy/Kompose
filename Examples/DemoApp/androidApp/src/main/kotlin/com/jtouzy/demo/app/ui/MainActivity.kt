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
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { KomposeApp() }
    }

    override fun onBackPressed() {
        if (!NavigationManager.popBackStack()) super.onBackPressed()
    }

    @Composable
    private fun KomposeApp() {
        MaterialTheme(colors = themeColors) {
            Crossfade(NavigationManager.currentScreen) { screen ->
                Surface(color = (+MaterialTheme.colors()).background) {
                    when (screen) {
                        Screen.Home -> get<CharactersScreen>().MainScreen()
                        is Screen.Quote -> get<QuoteScreen> { parametersOf(screen.character) }.MainScreen()
                    }
                }
            }
        }
    }
}
