package com.mathias8dev.edt.ui.screens.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import com.google.accompanist.pager.*
import com.mathias8dev.edt.domain.persistence.model.EdtAndTask
import com.mathias8dev.edt.domain.viewModel.EdtViewModel
import kotlinx.coroutines.launch




@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState, tabs: List<String>) {

    val scope = rememberCoroutineScope()

    ScrollableTabRow(

        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,

        contentColor = Color.White,

        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.Unspecified
            )
        }
    ) {

        tabs.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                text = {
                    Text(
                        tabs[index],
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
fun TabsContent(pagerState: PagerState, pagesCount: Int, state: List<EdtAndTask>, onAddTaskClicked: (Long)->Unit, onModifyEdtClicked: (Long)->Unit, onTaskClicked: (Long)->Unit ) {
    HorizontalPager(state = pagerState, count = pagesCount) {
            HomeScreenContent(state, onAddTaskClicked, onModifyEdtClicked, onTaskClicked)
    }
}