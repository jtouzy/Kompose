package com.jtouzy.demo.ui.characterquotes

import com.jtouzy.demo.ui.ViewState
import com.jtouzy.demo.ui.model.Quote

sealed class CharacterQuotesViewState(open val title: String) : ViewState {
    data class Loading(override val title: String) : CharacterQuotesViewState(title)
    data class Content(override val title: String, val quotes: List<Quote>) : CharacterQuotesViewState(title)
}
