package com.jtouzy.demo.ui.characters

import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.utils.applicationDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MarvelCharactersPresenterImpl(
    private val store: Store<MarvelCharactersViewState>
) : MarvelCharactersPresenter {

    override fun loadCharacters() {
        GlobalScope.launch(applicationDispatcher) {
            delay(1000)
            store.update(Content(listOf("Hulk", "Iron Man", "Thor")))
        }
    }
}
