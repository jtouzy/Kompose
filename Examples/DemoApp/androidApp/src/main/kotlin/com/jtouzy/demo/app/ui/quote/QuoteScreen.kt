package com.jtouzy.demo.app.ui.quote

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Center
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import com.jtouzy.demo.app.R
import com.jtouzy.demo.app.ui.NavigationManager
import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.app.ui.common.ErrorScreen
import com.jtouzy.demo.app.ui.common.LoadingScreen
import com.jtouzy.demo.app.ui.common.VectorImageButton
import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.ui.model.Quote
import com.jtouzy.demo.ui.quotes.QuotesPresenter
import com.jtouzy.demo.ui.quotes.QuotesPresenterImpl
import com.jtouzy.demo.ui.quotes.QuotesViewState

class QuoteScreen(
    private val navigationManager: NavigationManager,
    private val store: Store<QuotesViewState>,
    presenter: QuotesPresenter
) {

    init {
        presenter.loadQuotes()
    }

    @Composable
    private fun MainScreen() {
        Column {
            TopAppBar(
                title = { Text(store.viewState.title) },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_back) {
                        navigationManager.pop()
                    }
                }
            )
            Crossfade(store.viewState) { state ->
                when (state) {
                    is QuotesViewState.Loading -> LoadingScreen()
                    is QuotesViewState.Content -> QuoteList(state.quotes)
                    is QuotesViewState.NoQuote -> NoQuote()
                    is QuotesViewState.Error -> ErrorScreen()
                }
            }
        }
    }

    @Composable
    private fun QuoteList(quotes: List<Quote>) {
        VerticalScroller {
            Column {
                quotes.forEach { QuoteItem(it) }
            }
        }
    }

    @Composable
    private fun NoQuote() {
        Center {
            Text(text = +stringResource(R.string.no_quotes))
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
            Divider(color = (+MaterialTheme.colors()).onBackground)
        }
    }

    companion object {
        fun show(navigationManager: NavigationManager, dataStore: DataStore, character: Character) {
            val store = ObservableStore<QuotesViewState>(QuotesViewState.Loading(character.name))
            val presenter = QuotesPresenterImpl(store, dataStore, character)
            QuoteScreen(navigationManager, store, presenter).MainScreen()
        }
    }
}
