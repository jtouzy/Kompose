package com.jtouzy.demo.app.ui.quote

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.TopAppBar
import com.jtouzy.demo.app.R
import com.jtouzy.demo.app.ui.NavigationManager
import com.jtouzy.demo.app.ui.VectorImageButton

@Composable
fun QuoteScreen() {
    Column {
        TopAppBar(
            title = { Text("Details") },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_back) {
                    NavigationManager.popBackStack()
                }
            })
        Padding(padding = 16.dp) {
            Text(text = "Quote")
        }
    }
}
