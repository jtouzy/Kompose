package com.jtouzy.demo.ui.characters

import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.utils.ioDispatcher
import com.jtouzy.demo.utils.mainDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersPresenterImpl(
    private val store: Store<CharactersViewState>,
    private val dataStore: DataStore
) : CharactersPresenter {

    override fun loadCharacters() {
        GlobalScope.launch(mainDispatcher) {
            store.currentState = CharactersViewState.Loading
            val characters = withContext(ioDispatcher) { dataStore.getCharacters() }
            store.currentState = CharactersViewState.Content(characters.map { Character(it) })
        }
    }
}
