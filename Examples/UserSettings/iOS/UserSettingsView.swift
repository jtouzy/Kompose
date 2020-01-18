struct UserSettingsView: View {
    let presenter: UserSettingsPresenter
    @ObservedObject var observed: Observed<UserViewState>
    var viewState: UserViewState { observed.viewState }

    var body: some View {
        VStack {
            if observed.viewState == .display {
                Text("Display state")
                Button(action: { self.presenter.onValidateTap() }) { Text("Validate") }
            } else if observed.viewState == .editing {
                Text("Hello, it's loading state")
            }
        }
    }
}
