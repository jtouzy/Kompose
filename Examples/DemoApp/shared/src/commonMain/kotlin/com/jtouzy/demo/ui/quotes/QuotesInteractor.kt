package com.jtouzy.demo.ui.quotes

import com.jtouzy.demo.ui.model.Character

interface QuotesInteractor {
    suspend fun asyncLoadQuotes(character: Character)
    fun loadQuotes(character: Character)
}
