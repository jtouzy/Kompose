package com.jtouzy.demo.marvelcharacters

import com.jtouzy.demo.ui.Store

class MarvelCharactersPresenterImpl(
    private val store: Store<MarvelCharactersViewState>
): MarvelCharactersPresenter {

    override fun loadCharaters() {
        store.update(
            Content(listOf("Hulk", "Iron Man", "Thor"))
        )
    }
}