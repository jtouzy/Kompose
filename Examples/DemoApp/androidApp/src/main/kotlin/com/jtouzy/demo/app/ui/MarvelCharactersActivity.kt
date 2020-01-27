package com.jtouzy.demo.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.Content
import com.jtouzy.demo.ui.characters.Loading
import com.jtouzy.demo.ui.characters.MarvelCharactersPresenter
import com.jtouzy.demo.ui.characters.MarvelCharactersViewState
import org.koin.android.ext.android.inject

class MarvelCharactersActivity : AppCompatActivity() {

    private val presenter by inject<MarvelCharactersPresenter>()
    private val store by inject<Store<MarvelCharactersViewState>>()

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
                when(store.currentState) {
                    Loading -> getLoadingView()
                    is Content -> getContentView(store.currentState as Content)
                }
            }
        }
    }

    @Composable
    fun getLoadingView() {
        Text(text = "Loading")
    }

    @Composable
    fun getContentView(state: Content) {
        VerticalScroller {
            Column {
                state.characters.forEach {
                    Padding(16.dp) { Text(text = it) }
                }
            }
        }
    }
}
