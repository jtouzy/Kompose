//
//  MarvelCharactersView.swift
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
struct MarvelCharactersView: View {
    let presenter: MarvelCharactersPresenter
    @ObservedObject var store: ObservableStore<MarvelCharactersViewState>
    var viewState: MarvelCharactersViewState { store.state }

    var body: some View {
        NavigationView {
            VStack {
                dynamicContainedView()
            }
            .navigationBarTitle("Marvel Characters")
        }
        .onAppear { self.onViewAppear() }
    }

    func dynamicContainedView() -> AnyView {
        if viewState is Loading {
            return AnyView(Text("Loading"))
        } else if let contentViewState = viewState as? Content {
            return AnyView(
                List(contentViewState.characters) { character in
                    NavigationLink(destination: CharacterProfileAssembler.assemble()) {
                        HStack {
                            Image(systemName: "heart.fill")
                            Text(character)
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
extension MarvelCharactersView {
    func onViewAppear() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 2, execute: {
            self.presenter.loadCharaters()
        })
    }
}
