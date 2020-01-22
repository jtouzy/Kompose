//
//  DispatcherImpl.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

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
