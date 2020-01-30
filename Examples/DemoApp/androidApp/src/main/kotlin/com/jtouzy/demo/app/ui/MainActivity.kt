package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.jtouzy.demo.app.ui.characters.CharactersScreen
import com.jtouzy.demo.app.ui.common.themeColors
import com.jtouzy.demo.app.ui.quote.QuoteScreen
import com.jtouzy.demo.cache.DataStore
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val dataStore by inject<DataStore>()

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
                Screen.Home -> CharactersScreen.create(dataStore)
                is Screen.Quote -> QuoteScreen.create(dataStore, screen.character)
            }
        }
    }
}
