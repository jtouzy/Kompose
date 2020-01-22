//
//  MarvelCharactersAssembler.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

class MarvelCharactersAssembler {
    static func assemble() -> MarvelCharactersView {
        let dispatcher = DispatcherImpl<MarvelCharactersViewState>(baseState: .loading)
        let presenter = MarvelCharactersPresenterImpl(dispatcher: dispatcher)
        return MarvelCharactersView(presenter: presenter, observed: dispatcher.observed)
    }
}
