package com.mathias8dev.edt.ui.screens.colorSelector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mathias8dev.edt.domain.data.TSCSState
import com.mathias8dev.edt.domain.viewModel.EdtColorsViewModel
import com.mathias8dev.edt.ui.components.EdtPreviewer
import com.mathias8dev.edt.utils.Strings
import com.mathias8dev.edt.utils.lighterVariant


@Composable
fun ColorSelectorScreen(
    onColorSelected: (Color) -> Unit = {},
    selectorViewModel: EdtColorsViewModel = viewModel()
) {

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(backgroundColor = Color.White) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.ArrowBack, contentDescription = Strings.goBack)
                }

                Text(
                    text = Strings.selectColor, style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(contentPadding = PaddingValues(horizontal = 10.dp)) {
            itemsIndexed(selectorViewModel.tsColors) { _, state ->

                ThreeStateColorSelector(state = state.value) { color ->
                    selectorViewModel.update(state, color)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@Preview(name = "colorSelectorScreen", showSystemUi = true)
@Composable
fun ColorSelectorScreenPreview() {
    EdtPreviewer {
        ColorSelectorScreen()
    }
}


@Composable
fun PreviewT() {
    var state by remember {
        mutableStateOf(
            TSCSState(
                Color.Black,
                Color.Blue,
                Color.Green,
                Color.Blue
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(color = Color.White)
    ) {
        ThreeStateColorSelector(state) { state = state.copy(selectedColor = it) }
    }
}

@Composable
fun ThreeStateColorSelector(state: TSCSState, onColorSelected: (Color) -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            SelectableColor(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clickable { onColorSelected(state.colorOne) },
                color = state.colorOne,
                selected = state.selectedColor == state.colorOne
            )
            SelectableColor(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clickable { onColorSelected(state.colorTwo) },
                color = state.colorTwo,
                selected = state.selectedColor == state.colorTwo
            )
            SelectableColor(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clickable { onColorSelected(state.colorThree) },
                color = state.colorThree,
                selected = state.selectedColor == state.colorThree
            )
        }
    }
}

@Composable
fun SelectableColor(modifier: Modifier, color: Color, selected: Boolean = false) {
    Box(
        modifier = modifier.then(Modifier.background(color = color))
    ) {
        if (selected) {
            IconButton(onClick = {}, modifier = Modifier.align(Alignment.BottomEnd)) {
                Icon(Icons.Default.Done, contentDescription = "", tint = Color.White)
            }
        }
    }
}