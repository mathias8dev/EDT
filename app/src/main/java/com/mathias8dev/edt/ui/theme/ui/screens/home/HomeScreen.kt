package com.mathias8dev.edt.ui.theme.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mathias8dev.edt.ui.theme.greenColor


@Preview(showBackground = true)
@OptIn(ExperimentalPagerApi::class, ExperimentalUnitApi::class)
@Composable
fun HomeScreen() {

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        TopAppBar(backgroundColor = greenColor) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tab Layout Example",
                    style = TextStyle(color = Color.White),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(all = 5.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}