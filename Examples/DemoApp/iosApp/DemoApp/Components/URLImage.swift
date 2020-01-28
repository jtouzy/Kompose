//
//  URLImage.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 28/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import SwiftUI

class ObservableImageData: ObservableObject {
    @Published var data = Data()

    init(imageURL: String) {
        guard let url = URL(string: imageURL) else { return }
        URLSession.shared.dataTask(with: url) { data, _, _ in
            guard let data = data else { return }
            DispatchQueue.main.async {
                self.data = data
            }
        }.resume()
    }
}

struct URLImage: View {
    @ObservedObject var imageData: ObservableImageData

    var body: some View {
        dynamicImage()
    }

    func dynamicImage() -> AnyView {
        if let uiImage = UIImage(data: imageData.data) {
            return AnyView(
                Image(uiImage: uiImage)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(height: 60)
                    .clipShape(Circle())
                    .shadow(radius: 20)
            )
        } else {
            return AnyView(
                ActivityIndicator(style: .medium)
            )
        }
    }
}
