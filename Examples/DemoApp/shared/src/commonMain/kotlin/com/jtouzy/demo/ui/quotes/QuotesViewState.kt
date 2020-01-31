package com.jtouzy.demo.ui.quotes

import com.jtouzy.demo.ui.ViewState
import com.jtouzy.demo.ui.model.Quote

sealed class QuotesViewState(open val title: String) : ViewState {
    data class Loading(override val title: String) : QuotesViewState(title)
    data class Content(override val title: String, val quotes: List<Quote>) : QuotesViewState(title)
    data class NoQuote(override val title: String) : QuotesViewState(title)
    data class Error(override val title: String) : QuotesViewState(title)
}
