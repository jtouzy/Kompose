//
//  ObservableStore.swift
//  $$PROJECT_IDENTIFIER$$
//
//  Created by $$AUTHOR$$ on $$YEAR$$.
//  Copyright © $$YEAR$$ $$AUTHOR$$. All rights reserved.
//

import shared

class ObservableStore<T: ViewState>: Store, ObservableObject {
    var viewState: ViewState {
        didSet {
            guard let newState = viewState as? T else {
                fatalError("Cannot be casted")
            }
            state = newState
        }
    }
    @Published var state: T

    init(baseState: T) {
        viewState = baseState
        state = baseState
    }
}
