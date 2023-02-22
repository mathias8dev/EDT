package com.mathias8dev.edt.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


@Composable
fun AnimatedVisibilityColumn(
    visibility: Boolean,
    modifier: Modifier = Modifier,
    children: @Composable() () -> Unit
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = shrinkVertically() + fadeOut(targetAlpha = 0.5f)
    ) {
        Column(modifier = modifier) {
            children()
        }
    }
}