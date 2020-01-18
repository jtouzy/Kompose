class UserSettingsPresenterImpl(private val dispatcher: Dispatcher<UserViewState>): UserSettingsPresenter {
    override fun onValidate() {
        dispatcher.update(UserViewState.LOADING)
    }
}
