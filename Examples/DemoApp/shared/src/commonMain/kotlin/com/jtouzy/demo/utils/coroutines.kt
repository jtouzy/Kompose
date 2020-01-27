package com.jtouzy.demo.utils

import kotlinx.coroutines.CoroutineDispatcher

expect val mainDispatcher: CoroutineDispatcher
expect val ioDispatcher: CoroutineDispatcher