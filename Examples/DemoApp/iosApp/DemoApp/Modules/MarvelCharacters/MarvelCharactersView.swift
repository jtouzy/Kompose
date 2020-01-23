//
//  MarvelCharactersView.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 22/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import SwiftUI
import shared

struct MarvelCharactersView: View {
    let presenter: MarvelCharactersPresenter
    @ObservedObject var store: ObservableStore<MarvelCharactersViewState>
    var viewState: MarvelCharactersViewState { store.state }

    var body: some View {
        VStack {
            if viewState == .loading {
                Text("Loading")
            } else {
                Text("TODO")
            }
        }
    }
}
