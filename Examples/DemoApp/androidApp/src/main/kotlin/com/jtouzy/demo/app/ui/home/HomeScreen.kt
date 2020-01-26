package com.jtouzy.demo.app.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Center
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import com.jtouzy.demo.app.ui.Screen
import com.jtouzy.demo.app.ui.ScreenProvider
import com.jtouzy.demo.ui.model.CatFact

@Composable
fun HomeScreen(facts: List<CatFact>) {
    Column {
        TopAppBar(title = { Text("Cat Facts") })
        VerticalScroller {
            Column {
                facts.forEach { FactRow(it) }
            }
        }
    }
}

@Composable
private fun FactRow(catFact: CatFact) {
    Column {
        Ripple(bounded = true) {
            Padding(16.dp) {
                Clickable(onClick = {
                    ScreenProvider.navigateTo(Screen.Details(catFact))
                }) {
                    Text(text = catFact.fact)
                }
            }
        }
        Divider(color = Color(0x14333333))
    }
}

@Composable
fun LoadingScreen() {
    Center {
        Text(text = "Loading...")
    }
}
