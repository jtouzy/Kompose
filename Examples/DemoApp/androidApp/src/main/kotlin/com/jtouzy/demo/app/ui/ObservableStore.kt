package com.jtouzy.demo.app.ui

import androidx.compose.Model
import com.jtouzy.demo.ui.Store
import com.jtouzy.demo.ui.ViewState

@Model
class ObservableStore<T: ViewState>(override var currentState: T) : Store<T> {
}
