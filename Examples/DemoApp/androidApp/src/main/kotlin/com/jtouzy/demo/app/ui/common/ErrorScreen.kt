package com.jtouzy.demo.app.ui.common

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.layout.Center
import androidx.ui.res.stringResource
import com.jtouzy.demo.app.R

@Composable
fun ErrorScreen() {
    Center {
        Text(text = +stringResource(R.string.generic_message_error))
    }
}
