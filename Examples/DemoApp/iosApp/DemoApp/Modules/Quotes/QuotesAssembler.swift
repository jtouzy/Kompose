//
//  QuotesAssembler.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 24/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

class QuotesAssembler {
    static func assemble(for character: Character) -> QuotesView {
        let store = ObservableStore<QuotesViewState>(
            baseState: QuotesViewState.Loading(title: character.name)
        )
        let useCase = QuotesUseCase(
            store: store, dataStore: Factory.dataStore
        )
        return QuotesView(useCase: useCase, store: store, character: character)
    }
}
