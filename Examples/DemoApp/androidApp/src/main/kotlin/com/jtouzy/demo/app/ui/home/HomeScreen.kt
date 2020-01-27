package com.jtouzy.demo.app.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Center
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import com.jtouzy.demo.ui.model.Character

@Composable
fun HomeScreen(characters: List<Character>) {
    VerticalScroller {
        Column {
            characters.forEach {
                Padding(16.dp) { Text(text = it.name) }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Center {
        Text(text = "Loading...")
    }
}
