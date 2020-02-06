//
//  SceneDelegate.swift
//  $$PROJECT_IDENTIFIER$$
//
//  Created by $$AUTHOR$$ on $$YEAR$$.
//  Copyright © $$YEAR$$ $$AUTHOR$$. All rights reserved.
//

import UIKit
import SwiftUI

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?

    func scene(_ scene: UIScene,
               willConnectTo session: UISceneSession,
               options connectionOptions: UIScene.ConnectionOptions) {
        // HERE YOU NEED TO DEFINE YOUR FIRST VIEW
        let baseView = BaseView()

        if let windowScene = scene as? UIWindowScene {
            let window = UIWindow(windowScene: windowScene)
            window.rootViewController = UIHostingController(rootView: baseView)
            self.window = window
            window.makeKeyAndVisible()
        }
    }
}
