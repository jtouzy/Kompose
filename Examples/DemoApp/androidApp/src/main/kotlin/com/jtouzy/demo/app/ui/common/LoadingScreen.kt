package com.jtouzy.demo.app.ui.common

import androidx.compose.Composable
import androidx.ui.layout.Center
import androidx.ui.material.CircularProgressIndicator


@Composable
fun LoadingScreen() {
    Center {
        CircularProgressIndicator()
    }
}
