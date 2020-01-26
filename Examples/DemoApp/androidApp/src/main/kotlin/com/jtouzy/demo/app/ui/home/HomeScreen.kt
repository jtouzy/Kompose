package com.jtouzy.demo.app.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Center
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.ripple.Ripple
import com.jtouzy.demo.app.ui.ScreenProvider
import com.jtouzy.demo.ui.model.CatFact

@Composable
fun HomeScreen(screenProvider: ScreenProvider, facts: List<CatFact>) {
    VerticalScroller {
        Column {
            facts.forEach {
                Ripple(bounded = true) {
                    Padding(16.dp) {
                        Clickable(onClick = {
                            screenProvider.goToDetails(it)
                        }) {
                            Text(text = it.fact)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Center {
        Text(text = "Loading...")
    }
}
