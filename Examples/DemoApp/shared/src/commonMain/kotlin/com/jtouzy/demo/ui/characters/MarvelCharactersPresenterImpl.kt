package com.jtouzy.demo.ui.characters

import com.jtouzy.demo.network.MarvelApi
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.utils.applicationDispatcher
import com.jtouzy.demo.utils.ioDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarvelCharactersPresenterImpl(
    private val store: Store<MarvelCharactersViewState>,
    private val api: MarvelApi
) : MarvelCharactersPresenter {

    override fun loadCharacters() {
        GlobalScope.launch(applicationDispatcher) {
            withContext(ioDispatcher) {
                store.update(Content(api.getCharacters()))
            }
        }
    }
}
