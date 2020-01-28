package com.jtouzy.demo.app.ui.characters

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Center
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.stringResource
import com.jtouzy.demo.app.R
import com.jtouzy.demo.app.ui.NavigationManager
import com.jtouzy.demo.app.ui.Screen
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.CharactersViewState
import com.jtouzy.demo.ui.model.Character

@Composable
fun CharactersScreen(store: Store<CharactersViewState>) {
    Column {
        TopAppBar(title = { Text(text = +stringResource(R.string.app_name)) })
        when (val state = store.currentState) {
            CharactersViewState.Loading -> LoadingScreen()
            is CharactersViewState.Content -> CharacterList(state.characters)
        }
    }
}

@Composable
private fun CharacterList(characters: List<Character>) {
    VerticalScroller {
        Column {
            characters.forEach { CharacterItem(it) }
        }
    }
}

@Composable
private fun CharacterItem(character: Character) {
    Column {
        Ripple(bounded = true) {
            Padding(16.dp) {
                Clickable(onClick = {
                    NavigationManager.navigateTo(Screen.Quote)
                }) {
                    Text(text = character.name)
                }
            }
        }
        Divider(color = Color(0x14333333))
    }
}

@Composable
private fun LoadingScreen() {
    Center {
        Text(text = "Loading...")
    }
}
