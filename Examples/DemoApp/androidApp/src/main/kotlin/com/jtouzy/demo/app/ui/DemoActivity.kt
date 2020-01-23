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
import com.jtouzy.demo.marvelcharacters.MarvelCharactersPresenter
import org.koin.android.ext.android.inject

class DemoActivity : AppCompatActivity() {

    private val presenter by inject<MarvelCharactersPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { getContent() }
        presenter.loadCharaters()
    }

    @Composable
    fun displayCharacters() {
        VerticalScroller {
            Column {
                DemoStatus.characters.forEach {
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
