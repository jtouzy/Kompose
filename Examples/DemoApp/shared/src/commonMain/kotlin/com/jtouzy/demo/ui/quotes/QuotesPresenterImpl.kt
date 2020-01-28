package com.jtouzy.demo.ui.quotes

import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.ui.model.Quote
import com.jtouzy.demo.utils.ioDispatcher
import com.jtouzy.demo.utils.mainDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuotesPresenterImpl(
    private val store: Store<QuotesViewState>,
    private val dataStore: DataStore,
    private val character: Character
) : QuotesPresenter {

    override fun loadQuotes() {
        GlobalScope.launch(mainDispatcher) {
            store.currentState = QuotesViewState.Loading(character.name)
            val quotes = withContext(ioDispatcher) { dataStore.getCharacterQuotes(character.name) }
            store.currentState = if (quotes.isEmpty()) {
                QuotesViewState.NoQuote(character.name)
            } else {
                QuotesViewState.Content(
                    character.name,
                    quotes.map { Quote(it) }
                )
            }
        }
    }
}
