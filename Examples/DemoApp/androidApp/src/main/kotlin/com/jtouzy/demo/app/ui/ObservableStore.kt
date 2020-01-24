package com.jtouzy.demo.app.ui

import androidx.compose.Model
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.ViewState

@Model
class ObservableStore<T: ViewState>(var state: T) : Store<T> {
    override fun update(viewState: T) {
        state = viewState
    }
}
