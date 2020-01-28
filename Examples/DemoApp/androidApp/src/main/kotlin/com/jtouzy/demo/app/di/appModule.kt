package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.ui.characters.CharactersScreen
import com.jtouzy.demo.app.ui.quote.QuoteScreen
import com.jtouzy.demo.network.BreakingBadApi
import com.jtouzy.demo.ui.model.Character
import org.koin.dsl.module

val appModule = module {

    single { BreakingBadApi() }

    factory { CharactersScreen(get()) }
    factory { (character: Character) -> QuoteScreen(get(), character) }
}
