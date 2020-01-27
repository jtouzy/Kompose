package com.jtouzy.demo.ui

interface Store<T : ViewState> {
    fun update(viewState: T)
}
