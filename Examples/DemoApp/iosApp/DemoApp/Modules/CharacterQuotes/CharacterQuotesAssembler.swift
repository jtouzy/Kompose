//
//  CharacterQuotesAssembler.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 24/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

class CharacterQuotesAssembler {
    static func assemble(for character: Character) -> CharacterQuotesView {
        let store = ObservableStore<CharacterQuotesViewState>(
            baseState: CharacterQuotesViewState.Loading(title: character.name)
        )
        let presenter = CharacterQuotesPresenterImpl(
            store: store, api: BreakingBadApi(), character: character
        )
        return CharacterQuotesView(presenter: presenter, store: store)
    }
}
