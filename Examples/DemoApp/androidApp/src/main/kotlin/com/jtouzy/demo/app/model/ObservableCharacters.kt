package com.jtouzy.demo.app.model

import androidx.compose.Model

@Model
data class ObservableCharacters(var names: List<String> = emptyList())