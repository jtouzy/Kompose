package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.network.BreakingBadApi
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.CharactersPresenter
import com.jtouzy.demo.ui.characters.CharactersPresenterImpl
import com.jtouzy.demo.ui.characters.CharactersViewState
import com.jtouzy.demo.ui.characters.Loading
import org.koin.dsl.module

val appModule = module {

    single { BreakingBadApi() }
    single<Store<CharactersViewState>> { ObservableStore(currentState = Loading) }
    factory<CharactersPresenter> { CharactersPresenterImpl(get(), get()) }
}
