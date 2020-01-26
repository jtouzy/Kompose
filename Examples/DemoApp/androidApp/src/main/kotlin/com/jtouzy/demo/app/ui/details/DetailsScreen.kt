package com.jtouzy.demo.app.ui.details

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.TopAppBar
import com.jtouzy.demo.app.R
import com.jtouzy.demo.app.ui.ScreenProvider
import com.jtouzy.demo.app.ui.VectorImageButton
import com.jtouzy.demo.ui.model.CatFact

@Composable
fun DetailsScreen(fact: CatFact) {
    Column {
        TopAppBar(
            title = { Text("Details") },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_back) {
                    ScreenProvider.popBackStack()
                }
            })
        Padding(padding = 16.dp) {
            Text(text = fact.user)
        }
    }
}
