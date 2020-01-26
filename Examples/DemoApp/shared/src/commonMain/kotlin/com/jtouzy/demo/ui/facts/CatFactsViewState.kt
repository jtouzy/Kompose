package com.jtouzy.demo.ui.facts

import com.jtouzy.demo.ui.ViewState
import com.jtouzy.demo.ui.model.CatFact

sealed class CatFactsViewState : ViewState

object Loading : CatFactsViewState()

data class Content(val facts: List<CatFact>) : CatFactsViewState()
