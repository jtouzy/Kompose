package com.jtouzy.demo.cache

import com.jtouzy.demo.network.BreakingBadApi
import com.jtouzy.demo.network.dto.CharacterDto
import com.jtouzy.demo.network.dto.QuoteDto

class InMemoryDataStore(private val api: BreakingBadApi) : DataStore {

    private var characters: List<CharacterDto>? = null
    private val quotes: MutableMap<String, List<QuoteDto>> = mutableMapOf()

    override suspend fun getCharacters(): List<CharacterDto> =
        characters ?: api.getCharacters().also { characters = it }

    override suspend fun getCharacterQuotes(name: String): List<QuoteDto> =
        quotes[name] ?: api.getCharacterQuotes(name).also { quotes[name] = it }
}
