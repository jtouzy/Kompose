//
//  ObservableStore.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

class ObservableStore<T: ViewState>: Store, ObservableObject {
    @Published var state: T

    init(baseState: T) {
        state = baseState
    }
    func update(viewState: ViewState) {
        guard let observedViewState = viewState as? T else {
            fatalError("Cast problem")
        }
        state = observedViewState
    }
}
