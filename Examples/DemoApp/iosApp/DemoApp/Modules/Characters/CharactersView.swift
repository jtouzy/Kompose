//
//  CharactersView.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import SwiftUI
import shared

//
// MARK: View Layout
//
struct CharactersView: View {
    let presenter: CharactersPresenter
    @ObservedObject var store: ObservableStore<CharactersViewState>
    var viewState: CharactersViewState { store.state }

    var body: some View {
        NavigationView {
            VStack {
                dynamicContainedView()
            }
            .navigationBarTitle("Breaking Bad Quotes")
        }
        .onAppear { self.onViewAppear() }
    }

    func dynamicContainedView() -> AnyView {
        if viewState is CharactersViewState.Loading {
            return AnyView(ActivityIndicator(style: .large))
        } else if let contentViewState = viewState as? CharactersViewState.Content {
            return AnyView(
                List(contentViewState.characters) { character in
                    NavigationLink(destination:
                        QuotesAssembler.assemble(for: character)
                    ) {
                        HStack {
                            Image(systemName: "heart.fill")
                            Text(character.name)
                        }
                        .padding()
                    }
                }
                .listStyle(GroupedListStyle())
            )
        }
        return AnyView(Text("Unrecognized state"))
    }
}

//
// MARK: Events
//
extension CharactersView {
    func onViewAppear() {
        presenter.loadCharacters()
    }
}
