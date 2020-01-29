package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.jtouzy.demo.app.ui.characters.CharactersScreen
import com.jtouzy.demo.app.ui.common.themeColors
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
            when (val screen = NavigationManager.currentScreen) {
                Screen.Home -> get<CharactersScreen>().MainScreen()
                is Screen.Quote -> get<QuoteScreen> { parametersOf(screen.character) }.MainScreen()
            }
        }
    }
}
