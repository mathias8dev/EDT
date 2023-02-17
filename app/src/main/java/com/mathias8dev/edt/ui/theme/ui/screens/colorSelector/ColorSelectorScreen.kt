package com.mathias8dev.edt.ui.theme.ui.screens.colorSelector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathias8dev.edt.ui.theme.domain.data.TSCSState
import com.mathias8dev.edt.ui.theme.ui.Strings


@Composable
fun ColorSelectorScreen(
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
        Column(modifier = Modifier.padding(10.dp)) {

        }
    }
}


@Preview(name = "threeState")
@Composable
fun PreviewT() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(color = Color.White)
    ) {
        val state =
                TSCSState(
                    Color.Black,
                    Color.Blue,
                    Color.Green,
                    2
                )

        ThreeStateColorSelector(state) {}
    }
}

@Composable
fun ThreeStateColorSelector(state: TSCSState, onColorSelected: (Int) -> Unit) {
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
                    .clickable { onColorSelected(0) },
                color = state.colorOne,
                selected = state.selectedIndex == 0
            )
            SelectableColor(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clickable { onColorSelected(1) },
                color = state.colorTwo,
                selected = state.selectedIndex == 1
            )
            SelectableColor(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clickable { onColorSelected(2) },
                color = state.colorThree,
                selected = state.selectedIndex == 2
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
                Icon(Icons.Default.Done, contentDescription = "")
            }
        }
    }
}