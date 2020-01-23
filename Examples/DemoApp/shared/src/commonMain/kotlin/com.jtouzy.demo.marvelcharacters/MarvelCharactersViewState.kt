package com.jtouzy.demo.marvelcharacters

import com.jtouzy.demo.ui.ViewState

sealed class MarvelCharactersViewState : ViewState

object Loading : MarvelCharactersViewState()

data class Content(val characters: List<String>) : MarvelCharactersViewState()
