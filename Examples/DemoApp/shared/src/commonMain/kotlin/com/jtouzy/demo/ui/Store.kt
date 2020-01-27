package com.jtouzy.demo.ui

interface Store<T : ViewState> {
    var currentState: T
    fun update(viewState: T)
}
