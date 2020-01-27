//
//  CharacterQuotesView.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 24/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import SwiftUI
import shared

//
// MARK: View Layout
//
struct CharacterQuotesView: View {
    let presenter: CharacterQuotesPresenter
    @ObservedObject var store: ObservableStore<CharacterQuotesViewState>
    var viewState: CharacterQuotesViewState { store.state }

    var body: some View {
        VStack {
            dynamicContainedView()
        }
        .navigationBarTitle(viewState.title)
        .onAppear { self.onViewAppear() }
    }

    func dynamicContainedView() -> AnyView {
        if viewState is CharacterQuotesViewState.Loading {
            return AnyView(ActivityIndicator(style: .large))
        } else if let contentViewState = viewState as? CharacterQuotesViewState.Content {
            return AnyView(
                List(contentViewState.quotes) { quote in
                    VStack(alignment: .leading) {
                        Text(quote.quote)
                            .bold()
                        Text(quote.series)
                            .foregroundColor(.gray)
                    }
                    .padding()
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
extension CharacterQuotesView {
    func onViewAppear() {
        presenter.loadQuotesForCharacter()
    }
}
