package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.ui.ObservableStore
import com.jtouzy.demo.app.ui.characters.CharactersScreen
import com.jtouzy.demo.app.ui.quote.QuoteScreen
import com.jtouzy.demo.cache.DataStore
import com.jtouzy.demo.cache.InMemoryDataStore
import com.jtouzy.demo.network.BreakingBadApi
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.characters.CharactersPresenter
import com.jtouzy.demo.ui.characters.CharactersPresenterImpl
import com.jtouzy.demo.ui.characters.CharactersViewState
import com.jtouzy.demo.ui.model.Character
import com.jtouzy.demo.ui.quotes.QuotesPresenter
import com.jtouzy.demo.ui.quotes.QuotesPresenterImpl
import com.jtouzy.demo.ui.quotes.QuotesViewState
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {


    single { BreakingBadApi() }
    single<DataStore> { InMemoryDataStore(get()) }

    // Characters
    val characterQualifier = named("Characters")
    single<Store<CharactersViewState>>(characterQualifier) { ObservableStore(CharactersViewState.Loading) }
    factory<CharactersPresenter> { CharactersPresenterImpl(get(characterQualifier), get()) }
    factory { CharactersScreen(get(characterQualifier), get()) }

    // Quotes
    val quoteQualifier = named("Quotes")
    single<Store<QuotesViewState>>(quoteQualifier) { (character: Character) ->
        ObservableStore(QuotesViewState.Loading(character.name))
    }
    factory<QuotesPresenter> { (character: Character) ->
        QuotesPresenterImpl(get(quoteQualifier) { parametersOf(character) }, get(), character)
    }
    factory { (character: Character) ->
        QuoteScreen(get(quoteQualifier) { parametersOf(character) }, get { parametersOf(character) })
    }
}
