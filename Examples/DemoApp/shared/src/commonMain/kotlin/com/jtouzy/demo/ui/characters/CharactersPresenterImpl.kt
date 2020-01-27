package com.jtouzy.demo.ui.characters

import com.jtouzy.demo.network.BreakingBadApi
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.utils.ioDispatcher
import com.jtouzy.demo.utils.mainDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersPresenterImpl(
    private val store: Store<CharactersViewState>,
    private val api: BreakingBadApi
) : CharactersPresenter {

    override fun loadCharacters() {
        GlobalScope.launch(mainDispatcher) {
            store.currentState = CharactersViewState.Loading
            val characters = withContext(ioDispatcher) { api.getCharacters() }
            store.currentState = CharactersViewState.Content(characters.map { Character(it) })
        }
    }
}
