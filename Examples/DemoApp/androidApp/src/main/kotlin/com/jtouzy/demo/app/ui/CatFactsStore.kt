package com.jtouzy.demo.app.ui

import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.facts.CatFactsViewState
import com.jtouzy.demo.ui.facts.Content
import com.jtouzy.demo.ui.facts.Loading

class CatFactsStore(private val screenProvider: ScreenProvider) : Store<CatFactsViewState> {

    override fun update(viewState: CatFactsViewState) {
        screenProvider.screen = when (viewState) {
            Loading -> Screen.Loading
            is Content -> Screen.Home(viewState.facts)
        }
    }
}
