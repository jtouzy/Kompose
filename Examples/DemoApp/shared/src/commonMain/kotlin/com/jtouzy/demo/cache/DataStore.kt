package com.jtouzy.demo.cache

import com.jtouzy.demo.network.dto.CharacterDto
import com.jtouzy.demo.network.dto.QuoteDto

interface DataStore {
    suspend fun getCharacters(): List<CharacterDto>
    suspend fun getCharacterQuotes(name: String): List<QuoteDto>
}
