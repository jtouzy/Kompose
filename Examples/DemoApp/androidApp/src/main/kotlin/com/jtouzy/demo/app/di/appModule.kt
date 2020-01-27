package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.Loading
import com.jtouzy.demo.ui.characters.MarvelCharactersPresenter
import com.jtouzy.demo.ui.characters.MarvelCharactersPresenterImpl
import com.jtouzy.demo.ui.characters.MarvelCharactersViewState
import org.koin.dsl.module

val appModule = module {

    single<Store<MarvelCharactersViewState>> { ObservableStore(currentState = Loading) }
    factory<MarvelCharactersPresenter> { MarvelCharactersPresenterImpl(get()) }
}
