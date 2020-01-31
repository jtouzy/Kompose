package com.jtouzy.demo.common

import kotlinx.coroutines.runBlocking

internal actual fun <T> runTest(block: suspend () -> T): T {
    return runBlocking { block() }
}
