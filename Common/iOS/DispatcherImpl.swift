class DispatcherImpl<T: ViewState>: Dispatcher {
    let observed: Observed<T>

    init(baseState: T) {
        observed = Observed(viewState: baseState)
    }
    func update(viewState: ViewState) {
        guard let observedViewState = viewState as? T else {
            fatalError("Cast problem")
        }
        observed.viewState = observedViewState
    }
}

class Observed<T>: ObservableObject {
    @Published var viewState: T

    init(viewState: T) {
        self.viewState = viewState
    }
}
