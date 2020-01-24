package com.jtouzy.demo.app.ui

import androidx.compose.Model
import com.jtouzy.demo.app.model.ObservableCharacters
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.Content
import com.jtouzy.demo.ui.characters.Loading
import com.jtouzy.demo.ui.characters.MarvelCharactersViewState

@Model
class MarvelCharactersStore(private val characters: ObservableCharacters) : Store<MarvelCharactersViewState> {

    override fun update(viewState: MarvelCharactersViewState) {
        when (viewState) {
            Loading -> Unit
            is Content -> characters.names = viewState.characters
        }
    }
}
