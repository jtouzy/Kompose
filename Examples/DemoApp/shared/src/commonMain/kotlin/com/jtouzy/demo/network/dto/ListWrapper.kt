package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
class ListWrapper<T> {
    var items: List<T> = listOf()
}