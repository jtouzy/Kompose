package com.jtouzy.demo.app.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.ui.characters.CharactersUseCase
import com.jtouzy.demo.ui.characters.CharactersViewState
import kotlinx.coroutines.launch

class CharactersViewModel(dataStore: DataStore) : ViewModel() {

    val viewState: CharactersViewState
        get() = store.viewState
    private val store = ObservableStore<CharactersViewState>(CharactersViewState.Loading)
    private val useCase = CharactersUseCase(store, dataStore)

    fun loadCharacters() {
        viewModelScope.launch {
            useCase.asyncLoadCharacters()
        }
    }
}
