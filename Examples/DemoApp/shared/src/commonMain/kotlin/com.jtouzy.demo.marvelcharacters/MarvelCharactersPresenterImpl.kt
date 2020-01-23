package com.jtouzy.demo.marvelcharacters

import com.jtouzy.demo.ui.Store

class MarvelCharactersPresenterImpl(
    private val store: Store<MarvelCharactersViewState>
): MarvelCharactersPresenter {
    /**
     * Called when first view loading is ended.
     */
    override fun onViewLoadingEnded() {
        TODO("not implemented")
    }
}
