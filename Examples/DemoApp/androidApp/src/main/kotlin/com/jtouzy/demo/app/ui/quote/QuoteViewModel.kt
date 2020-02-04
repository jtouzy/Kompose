package com.jtouzy.demo.app.ui.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.ui.quotes.QuotesUseCase
import com.jtouzy.demo.ui.quotes.QuotesViewState
import kotlinx.coroutines.launch

class QuoteViewModel(dataStore: DataStore) : ViewModel() {

    val viewState: QuotesViewState
        get() = store.viewState
    private val store = ObservableStore<QuotesViewState>(QuotesViewState.Loading(""))
    private val useCase = QuotesUseCase(store, dataStore)

    fun loadQuote(character: Character) {
        viewModelScope.launch {
            useCase.asyncLoadQuotes(character)
        }
    }
}
