package com.jtouzy.demo.marvelcharacters

import com.jtouzy.demo.ui.Dispatcher

class MarvelCharactersPresenterImpl(
    private val dispatcher: Dispatcher<MarvelCharactersViewState>
): MarvelCharactersPresenter {
    /**
     * Called when first view loading is ended.
     */
    override fun onViewLoadingEnded() {
        TODO("not implemented")
    }
}
