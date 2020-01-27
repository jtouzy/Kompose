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
    @ObservedObject var store: ObservableStore<MarvelCharactersViewState>
    var viewState: MarvelCharactersViewState { store.state }

    var body: some View {
        VStack {
            containedView()
        }.onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2, execute: {
                self.presenter.loadCharacters()
            })
        }
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
