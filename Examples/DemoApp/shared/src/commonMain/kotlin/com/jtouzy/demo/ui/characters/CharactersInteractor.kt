package com.jtouzy.demo.ui.characters

interface CharactersInteractor {
    suspend fun asyncLoadCharacters()
    fun loadCharacters()
}
