package com.jtouzy.demo.ui.characters

import com.jtouzy.demo.network.MarvelCharacter
import com.jtouzy.demo.ui.ViewState

sealed class MarvelCharactersViewState : ViewState

object Loading : MarvelCharactersViewState()

data class Content(val characters: List<MarvelCharacter>) : MarvelCharactersViewState()
