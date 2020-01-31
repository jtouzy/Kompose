package com.jtouzy.demo.ui.characters

import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.network.dto.CharacterDto
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.model.Character
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import io.mockk.spyk
import kotlin.test.Test

abstract class CharactersPresenterTest {

    private val dtoCharacters = listOf(
        CharacterDto("Toto", "url"),
        CharacterDto("Titi", "url"),
        CharacterDto("Tutu", "url")
    )
    private val characters = listOf(
        Character("Toto", "url"),
        Character("Titi", "url"),
        Character("Tutu", "url")
    )

    private val store = spyk<Store<CharactersViewState>>()
    private val dataStore = mockk<DataStore> {
        coEvery { getCharacters() } returns dtoCharacters
    }
    private val presenter = CharactersPresenterImpl(store, dataStore)

    @Test
    fun `should return characters when call is successful`() {
        presenter.loadCharacters()

        coVerifyOrder {
            store.viewState = CharactersViewState.Loading
            store.viewState = CharactersViewState.Content(characters)
        }
    }

    @Test
    fun `should return an error when call is unsuccessful`() {
        coEvery { dataStore.getCharacters() } throws IllegalStateException("error")
        presenter.loadCharacters()

        coVerifyOrder {
            store.viewState = CharactersViewState.Loading
            store.viewState = CharactersViewState.Error
        }
    }
}
