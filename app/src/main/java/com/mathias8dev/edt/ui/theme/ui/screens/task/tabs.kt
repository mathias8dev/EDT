package com.mathias8dev.edt.ui.theme.ui.screens.task

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import com.google.accompanist.pager.*
import com.mathias8dev.edt.ui.theme.greenColor
import kotlinx.coroutines.launch




@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {

    val list = listOf(
        "Toutes les tâches",
        "FPGA",
        "Android Jetpack Compose",
        "Java Fx",
        "ReactJs",
        "VueJs",
    )

    val scope = rememberCoroutineScope()

    ScrollableTabRow(

        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        edgePadding = 0.dp,

        contentColor = Color.Black,

        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.Unspecified
            )
        }
    ) {

        list.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.Black else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = 7) {
            page ->
        when (page) {
            0 -> TaskScreenContent(data = "Welcome to all tasks Screen")
            1 -> TaskScreenContent(data = "Welcome to FPGA Screen")
            2 -> TaskScreenContent(data = "Welcome to Android jetpack compose Screen")
            3 -> TaskScreenContent(data = "Welcome to JavaFx Screen")
            4 -> TaskScreenContent(data = "Welcome to ReactJs Screen")
            5 -> TaskScreenContent(data = "Welcome to VueJs Screen")
        }
    }
}