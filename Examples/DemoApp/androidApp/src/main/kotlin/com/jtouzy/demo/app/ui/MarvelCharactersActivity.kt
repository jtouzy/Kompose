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
import com.jtouzy.demo.app.model.ObservableCharacters
import com.jtouzy.demo.ui.characters.MarvelCharactersPresenter
import org.koin.android.ext.android.inject

class MarvelCharactersActivity : AppCompatActivity() {

    private val presenter by inject<MarvelCharactersPresenter>()
    private val characters by inject<ObservableCharacters>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { getContent() }
        presenter.loadCharacters()
    }

    @Composable
    fun displayCharacters() {
        VerticalScroller {
            Column {
                characters.names.forEach {
                    Padding(16.dp) { Text(text = it) }
                }
            }
        }
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
                displayCharacters()
            }
        }
    }
}
