//
//  Observed.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import Combine

class Observed<T>: ObservableObject {
    @Published var viewState: T

    init(viewState: T) {
        self.viewState = viewState
    }
}
