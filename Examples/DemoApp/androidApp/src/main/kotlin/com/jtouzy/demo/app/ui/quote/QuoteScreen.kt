package com.jtouzy.demo.app.ui.quote

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Center
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import com.jtouzy.demo.app.R
import com.jtouzy.demo.app.ui.NavigationManager
import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.app.ui.VectorImageButton
import com.jtouzy.demo.app.ui.generic.LoadingScreen
import com.jtouzy.demo.network.BreakingBadApi
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.ui.model.Quote
import com.jtouzy.demo.ui.quotes.QuotesPresenterImpl
import com.jtouzy.demo.ui.quotes.QuotesViewState
import timber.log.Timber

class QuoteScreen(api: BreakingBadApi, character: Character) {

    private val store = ObservableStore<QuotesViewState>(QuotesViewState.Loading(character.name))
    private val presenter = QuotesPresenterImpl(store, api, character)

    init {
        presenter.loadQuotes()
    }

    @Composable
    fun MainScreen() {
        Column {
            TopAppBar(
                title = { Text(store.currentState.title) },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_back) {
                        NavigationManager.popBackStack()
                    }
                }
            )
            when (val state = store.currentState) {
                is QuotesViewState.Loading -> LoadingScreen()
                is QuotesViewState.Content -> QuoteList(state.quotes)
            }
        }
    }

    @Composable
    private fun QuoteList(quotes: List<Quote>) {
        if (quotes.isEmpty()) {
            NoQuote()
        } else {
            VerticalScroller {
                Column {
                    Timber.d(quotes.toString())
                    quotes.forEach { QuoteItem(it) }
                }
            }
        }
    }

    @Composable
    private fun NoQuote() {
        Center {
            Text(text = "No quote :(")
        }
    }

    @Composable
    private fun QuoteItem(quote: Quote) {
        Column {
            Padding(16.dp) {
                Text(text = quote.quote)
            }
            Padding(16.dp) {
                Text(text = quote.series)
            }
            Divider(color = Color(0x14333333))
        }
    }
}
