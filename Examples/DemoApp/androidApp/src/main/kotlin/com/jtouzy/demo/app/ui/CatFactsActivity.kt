package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import com.jtouzy.demo.app.ui.details.DetailsScreen
import com.jtouzy.demo.app.ui.home.HomeScreen
import com.jtouzy.demo.app.ui.home.LoadingScreen
import com.jtouzy.demo.ui.facts.CatFactsPresenter
import org.koin.android.ext.android.inject

class CatFactsActivity : AppCompatActivity() {

    private val presenter by inject<CatFactsPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CatApp() }
        presenter.loadCatFacts()
    }

    @Composable
    fun CatApp() {
        MaterialTheme(colors = themeColors) {
            AppContent()
        }
    }

    @Composable
    private fun AppContent() {
        Crossfade(ScreenProvider.currentScreen) { screen ->
            Surface(color = (+MaterialTheme.colors()).background) {
                when (screen) {
                    Screen.Loading -> LoadingScreen()
                    is Screen.Home -> HomeScreen(screen.facts)
                    is Screen.Details -> DetailsScreen(screen.fact)
                }
            }
        }
    }
}
