//
//  CharactersAssembler.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

class CharactersAssembler {
    static func assemble() -> CharactersView {
        let store = ObservableStore<CharactersViewState>(baseState: CharactersViewState.Loading())
        let useCase = CharactersUseCase(store: store, dataStore: Factory.dataStore)
        return CharactersView(useCase: useCase, store: store)
    }
}
