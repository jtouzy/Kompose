package com.jtouzy.demo.app.ui.details

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Padding
import com.jtouzy.demo.ui.model.CatFact

@Composable
fun DetailsScreen(fact: CatFact) {
    Padding(padding = 16.dp) {
        Text(text = fact.user)
    }
}
