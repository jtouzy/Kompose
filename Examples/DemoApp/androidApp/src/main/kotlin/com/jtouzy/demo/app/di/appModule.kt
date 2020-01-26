package com.jtouzy.demo.app.di

import com.jtouzy.demo.app.ui.CatFactsStore
import com.jtouzy.demo.app.ui.ScreenProvider
import com.jtouzy.demo.network.CatApi
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.facts.CatFactsPresenter
import com.jtouzy.demo.ui.facts.CatFactsPresenterImpl
import com.jtouzy.demo.ui.facts.CatFactsViewState
import org.koin.dsl.module

val appModule = module {

    single { ScreenProvider() }
    single { CatApi() }
    single<Store<CatFactsViewState>> { CatFactsStore(get()) }
    factory<CatFactsPresenter> { CatFactsPresenterImpl(get(), get()) }
}
