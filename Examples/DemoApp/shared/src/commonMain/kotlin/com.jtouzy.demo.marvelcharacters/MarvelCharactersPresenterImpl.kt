package com.jtouzy.demo.marvelcharacters

import com.jtouzy.demo.ui.Dispatcher

class MarvelCharactersPresenterImpl(
    private val dispatcher: Dispatcher<MarvelCharactersViewState>
) : MarvelCharactersPresenter {

    override fun loadCharaters() {
        dispatcher.update(
            Content(listOf("Hulk", "Iron Man", "Thor"))
        )
    }
}
