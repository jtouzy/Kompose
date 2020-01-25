package com.jtouzy.demo.app.ui

import com.jtouzy.demo.app.model.Character
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.Content
import com.jtouzy.demo.ui.characters.Loading
import com.jtouzy.demo.ui.characters.MarvelCharactersViewState

class MarvelCharactersStore(private val screenProvider: ScreenProvider) : Store<MarvelCharactersViewState> {

    override fun update(viewState: MarvelCharactersViewState) {
        screenProvider.screen = when (viewState) {
            Loading -> Screen.Loading
            is Content -> Screen.Home(viewState.characters.map { Character(it) })
        }
    }
}
