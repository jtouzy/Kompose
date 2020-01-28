//
//  Factory.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 28/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import Foundation
import shared

class Factory {
    static let api = BreakingBadApi()
    static let dataStore = InMemoryDataStore(api: api)
}
