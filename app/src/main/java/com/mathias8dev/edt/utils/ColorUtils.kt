package com.mathias8dev.edt.utils

import androidx.compose.ui.graphics.Color
import java.util.*

class ColorUtils {
    private val _colors = listOf(
        0xFFF44336, // Red
        0xFFE91E63, // Pink
        0xFF9C27B0, // Purple
        0xFF673AB7, // Deep Purple
        0xFF3F51B5, // Indigo
        0xFF2196F3, // Blue
        0xFF03A9F4, // Light Blue
        0xFF00BCD4, // Cyan
        0xFF009688, // Teal
        0xFF4CAF50, // Green
        0xFF8BC34A, // Light Green
        0xFFCDDC39, // Lime
        0xFFFFEB3B, // Yellow
        0xFFFFC107, // Amber
        0xFFFF9800, // Orange
        0xFFFF5722 // Deep Orange
    )

    val randomColor: Long
        get() = _colors[Random().nextInt(_colors.size)]

    val colors: List<Long>
        get() = _colors
}