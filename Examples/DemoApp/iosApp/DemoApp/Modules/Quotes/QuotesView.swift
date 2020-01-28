//
//  QuotesView.swift
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
struct QuotesView: View {
    let presenter: QuotesPresenter
    @ObservedObject var store: ObservableStore<QuotesViewState>
    var viewState: QuotesViewState { store.state }

    var body: some View {
        VStack {
            dynamicContainedView()
        }
        .navigationBarTitle(Text(viewState.title), displayMode: .inline)
        .onAppear { self.onViewAppear() }
    }

    func dynamicContainedView() -> AnyView {
        if viewState is QuotesViewState.Loading {
            return AnyView(
                ActivityIndicator(style: .large)
            )
        } else if viewState is QuotesViewState.NoQuote {
            return AnyView(
                Text("no_quotes")
            )
        } else if let contentViewState = viewState as? QuotesViewState.Content {
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
                .listStyle(PlainListStyle())
            )
        }
        return AnyView(Text("Unrecognized state"))
    }
}

//
// MARK: Events
//
extension QuotesView {
    func onViewAppear() {
        presenter.loadQuotes()
    }
}
