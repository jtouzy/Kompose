package com.jtouzy.demo.app.ui

import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.facts.CatFactsViewState
import com.jtouzy.demo.ui.facts.Content
import com.jtouzy.demo.ui.facts.Loading

class CatFactsStore : Store<CatFactsViewState> {

    override fun update(viewState: CatFactsViewState) {
        when (viewState) {
            Loading -> ScreenProvider.navigateTo(Screen.Loading)
            is Content -> ScreenProvider.navigateTo(Screen.Home(viewState.facts), true)
        }
    }
}
