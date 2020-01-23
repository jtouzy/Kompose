package com.jtouzy.demo.app.ui

import com.jtouzy.demo.marvelcharacters.Content
import com.jtouzy.demo.marvelcharacters.Loading
import com.jtouzy.demo.marvelcharacters.MarvelCharactersViewState
import com.jtouzy.demo.ui.Dispatcher

class MarvelCharactersDispatcher : Dispatcher<MarvelCharactersViewState> {

    override fun update(viewState: MarvelCharactersViewState) {
        when (viewState) {
            Loading -> Unit
            is Content -> DemoStatus.characters = viewState.characters
        }
    }
}
