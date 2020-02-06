package $$PROJECT_PACKAGE$$.app.ui

import androidx.compose.Model
import $$PROJECT_PACKAGE$$.ui.Store
import $$PROJECT_PACKAGE$$.ui.ViewState

@Model
class ObservableStore<T : ViewState>(override var viewState: T) : Store<T>
