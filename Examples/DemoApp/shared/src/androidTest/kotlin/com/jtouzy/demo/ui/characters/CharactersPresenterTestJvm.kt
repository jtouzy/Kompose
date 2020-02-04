package com.jtouzy.demo.ui.characters

import org.junit.Test

class CharactersPresenterTestJvm : CharactersPresenterTest() {

    @Test
    fun performTests() {
        `should return characters when call is successful`()
        `should return an error when call is unsuccessful`()
    }
}
