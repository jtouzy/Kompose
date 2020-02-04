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

class QuotesUseCase(
    private val store: Store<QuotesViewState>,
    private val dataStore: DataStore
) : QuotesInteractor {

    override suspend fun asyncLoadQuotes(character: Character) {
        store.viewState = QuotesViewState.Loading(character.name)
        try {
            val quotes = withContext(ioDispatcher) { dataStore.getCharacterQuotes(character.name) }
            store.viewState = if (quotes.isEmpty()) {
                QuotesViewState.NoQuote(character.name)
            } else {
                QuotesViewState.Content(character.name, quotes.map { Quote(it) })
            }
        } catch (exception: Exception) {
            store.viewState = QuotesViewState.Error(character.name)
        }
    }

    override fun loadQuotes(character: Character) {
        GlobalScope.launch(mainDispatcher) {
            asyncLoadQuotes(character)
        }
    }
}
