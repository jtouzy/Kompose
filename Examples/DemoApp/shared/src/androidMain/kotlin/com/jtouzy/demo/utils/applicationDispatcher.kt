package com.jtouzy.demo.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val applicationDispatcher: CoroutineDispatcher = Dispatchers.Main
