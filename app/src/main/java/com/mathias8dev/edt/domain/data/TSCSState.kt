package com.mathias8dev.edt.domain.data

import androidx.compose.ui.graphics.Color

data class TSCSState(
    val colorOne: Color,
    val colorTwo: Color,
    val colorThree: Color,
    var selectedColor: Color?
)
