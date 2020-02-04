package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.ui.NavigationManager
import com.jtouzy.demo.app.ui.characters.CharactersViewModel
import com.jtouzy.demo.app.ui.quote.QuoteViewModel
import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.cache.InMemoryDataStore
import com.jtouzy.demo.network.BreakingBadApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { NavigationManager() }
    single { BreakingBadApi() }
    single<DataStore> { InMemoryDataStore(get()) }

    viewModel { CharactersViewModel(get()) }
    viewModel { QuoteViewModel(get()) }
}
