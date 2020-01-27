package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import com.jtouzy.demo.app.ui.home.HomeScreen
import com.jtouzy.demo.app.ui.home.LoadingScreen
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.CharactersPresenter
import com.jtouzy.demo.ui.characters.CharactersViewState
import com.jtouzy.demo.ui.characters.Content
import com.jtouzy.demo.ui.characters.Loading
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val presenter by inject<CharactersPresenter>()
    private val store by inject<Store<CharactersViewState>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { getContent() }
        presenter.loadCharacters()
    }

    @Preview
    @Composable
    fun getContent() {
        MaterialTheme(
            colors = ColorPalette(
                primary = Color(0xFF, 0x00, 0x00)
            )
        ) {
            Column {
                TopAppBar(title = { Text("Demo") })
                when (val state = store.currentState) {
                    Loading -> LoadingScreen()
                    is Content -> HomeScreen(state.characters)
                }
            }
        }
    }
}
