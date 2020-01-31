//
//  ObservableStore.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

class ObservableStore<T: ViewState>: Store, ObservableObject {
    var currentState: ViewState {
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
