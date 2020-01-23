//
//  MarvelCharactersView.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import SwiftUI
import shared

extension String: Identifiable {
    public var id: String {
        return self
    }
}

struct MarvelCharactersView: View {
    let presenter: MarvelCharactersPresenter
    @ObservedObject var observed: Observed<MarvelCharactersViewState>
    var viewState: MarvelCharactersViewState { observed.viewState }

    var body: some View {
        VStack {
            containedView()
        }.onAppear { self.presenter.loadCharaters() }
    }

    func containedView() -> AnyView {
        if viewState is Loading {
            return AnyView(Text("Loading"))
        } else if let contentViewState = viewState as? Content {
            return AnyView(
                List(contentViewState.characters) { character in
                    Text(character)
                }
            )
        }
        return AnyView(Text("Unrecognized state"))
    }
}
