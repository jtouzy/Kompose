package com.jtouzy.demo.ui.characterquotes

import com.jtouzy.demo.network.BreakingBadApi
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.ui.model.Quote
import com.jtouzy.demo.utils.ioDispatcher
import com.jtouzy.demo.utils.mainDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterQuotesPresenterImpl(
    private val store: Store<CharacterQuotesViewState>,
    private val api: BreakingBadApi,
    private val character: Character
) : CharacterQuotesPresenter {

    override fun loadQuotesForCharacter() {
        GlobalScope.launch(mainDispatcher) {
            store.currentState = CharacterQuotesViewState.Loading(title = character.name)
            val quotes = withContext(ioDispatcher) { api.getCharacterQuotes(character.name) }
            store.currentState = CharacterQuotesViewState.Content(
                title = character.name,
                quotes = quotes.map { Quote(it) }
            )
        }
    }
}
