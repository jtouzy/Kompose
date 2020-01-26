package com.jtouzy.demo.ui.facts

import com.jtouzy.demo.network.CatApi
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.utils.ioDispatcher
import com.jtouzy.demo.utils.mainDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatFactsPresenterImpl(
    private val store: Store<CatFactsViewState>,
    private val api: CatApi
) : CatFactsPresenter {

    override fun loadCatFacts() {
        GlobalScope.launch(mainDispatcher) {
            store.update(Loading)
            val facts = withContext(ioDispatcher) {
                Content(api.getFacts())
            }
            store.update(facts)
        }
    }
}
