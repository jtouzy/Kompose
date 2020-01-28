//
//  UI+extensions.swift
//  DemoApp
//
//  Created by Jérémy TOUZY on 24/01/2020.
//  Copyright © 2020 jtouzy. All rights reserved.
//

import shared

extension Character: Identifiable {
    public var id: String {
        return name
    }
}

extension Quote: Identifiable {
    public var id: String {
        return quote
    }
}
