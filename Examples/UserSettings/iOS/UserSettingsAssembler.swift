class UserSettingsAssembler {
    static func assemble() -> UserSettingsView {
        let dispatcher = DispatcherImpl<UserViewState>(baseState: .display)
        let presenter = UserSettingsPresenterImpl(dispatcher: dispatcher)
        return UserSettingsView(presenter: presenter, observed: dispatcher.observed)
    }
}
