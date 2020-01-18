interface Dispatcher<T: ViewState> {
    fun update(viewState: T)
}
