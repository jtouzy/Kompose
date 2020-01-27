//
//  ActivityIndicator.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 27/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import SwiftUI

//
// MARK: Component ActivityIndicator
//
struct ActivityIndicator: UIViewRepresentable {
    let style: UIActivityIndicatorView.Style

    func makeUIView(context: UIViewRepresentableContext<ActivityIndicator>) -> UIActivityIndicatorView {
        return UIActivityIndicatorView(style: style)
    }

    func updateUIView(_ uiView: UIActivityIndicatorView,
                      context: UIViewRepresentableContext<ActivityIndicator>) {
        if (!uiView.isAnimating) {
            uiView.startAnimating()
        }
    }
}
