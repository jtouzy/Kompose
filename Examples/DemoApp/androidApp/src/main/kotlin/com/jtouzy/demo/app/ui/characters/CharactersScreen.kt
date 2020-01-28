package com.jtouzy.demo.app.ui.characters

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.stringResource
import com.jtouzy.demo.app.R
import com.jtouzy.demo.app.ui.NavigationManager
import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.app.ui.Screen
import com.jtouzy.demo.app.ui.generic.LoadingScreen
import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.ui.characters.CharactersPresenterImpl
import com.jtouzy.demo.ui.characters.CharactersViewState
import com.jtouzy.demo.ui.model.Character

class CharactersScreen(dataStore: DataStore) {

    private val store = ObservableStore<CharactersViewState>(CharactersViewState.Loading)
    private val presenter = CharactersPresenterImpl(store, dataStore)

    init {
        presenter.loadCharacters()
    }

    @Composable
    fun MainScreen() {
        Column {
            TopAppBar(title = { Text(text = +stringResource(R.string.app_name)) })
            Crossfade(store.currentState) { state ->
                when (state) {
                    CharactersViewState.Loading -> LoadingScreen()
                    is CharactersViewState.Content -> CharacterList(state.characters)
                }
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
        Ripple(bounded = true) {
            Clickable(onClick = {
                NavigationManager.navigateTo(Screen.Quote(character))
            }) {
                Column {
                    Padding(16.dp) {
                        Text(text = character.name)
                    }
                    Divider(color = Color(0x14333333))
                }
            }
        }
    }
}
