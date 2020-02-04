package com.jtouzy.demo.common

internal expect fun <T> runTest(block: suspend () -> T): T