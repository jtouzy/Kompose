package com.jtouzy.demo.ui

interface Store<T : ViewState> {
    var viewState: T
}
