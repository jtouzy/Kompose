package com.jtouzy.demo.common

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
internal actual fun <T> runTest(block: suspend () -> T): T {
    return runBlocking(TestCoroutineDispatcher()) { block() }
}
