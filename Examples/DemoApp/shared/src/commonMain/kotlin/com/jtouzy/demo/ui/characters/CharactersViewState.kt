package com.jtouzy.demo.ui.characters

import com.jtouzy.demo.ui.ViewState
import com.jtouzy.demo.ui.model.Character

sealed class CharactersViewState : ViewState

object Loading : CharactersViewState()

data class Content(val characters: List<Character>) : CharactersViewState()
