package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.model.ObservableCharacters
import com.jtouzy.demo.app.ui.MarvelCharactersStore
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.MarvelCharactersPresenter
import com.jtouzy.demo.ui.characters.MarvelCharactersPresenterImpl
import com.jtouzy.demo.ui.characters.MarvelCharactersViewState
import org.koin.dsl.module

val appModule = module {

    single { ObservableCharacters() }
    single<Store<MarvelCharactersViewState>> { MarvelCharactersStore(get()) }
    factory<MarvelCharactersPresenter> { MarvelCharactersPresenterImpl(get()) }
}
