package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.ui.MarvelCharactersDispatcher
import com.jtouzy.demo.marvelcharacters.MarvelCharactersPresenter
import com.jtouzy.demo.marvelcharacters.MarvelCharactersPresenterImpl
import com.jtouzy.demo.marvelcharacters.MarvelCharactersViewState
import com.jtouzy.demo.ui.Dispatcher
import org.koin.dsl.module

val appModule = module {

    single<Dispatcher<MarvelCharactersViewState>> { MarvelCharactersDispatcher() }
    factory<MarvelCharactersPresenter> { MarvelCharactersPresenterImpl(get()) }
}
