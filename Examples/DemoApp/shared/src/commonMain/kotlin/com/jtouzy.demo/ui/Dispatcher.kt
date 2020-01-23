package com.jtouzy.demo.ui

interface Dispatcher<T : ViewState> {
    fun update(viewState: T)
}
