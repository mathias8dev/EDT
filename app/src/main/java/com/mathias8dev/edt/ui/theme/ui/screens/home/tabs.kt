package com.mathias8dev.edt.ui.theme.ui.screens.home

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
        "Monday",
        "Tuesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
    )

    val scope = rememberCoroutineScope()

    ScrollableTabRow(

        selectedTabIndex = pagerState.currentPage,
        backgroundColor = greenColor,
        edgePadding = 0.dp,

        contentColor = Color.White,

        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {

        list.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
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
            0 -> HomeScreenContent(data = "Welcome to Monday Screen")
            1 -> HomeScreenContent(data = "Welcome to Tuesday Screen")
            2 -> HomeScreenContent(data = "Welcome to Wednesday Screen")
            3 -> HomeScreenContent(data = "Welcome to Thursday Screen")
            4 -> HomeScreenContent(data = "Welcome to Friday Screen")
            5 -> HomeScreenContent(data = "Welcome to Saturday Screen")
            6 -> HomeScreenContent(data = "Welcome to Sunday Screen")
        }
    }
}