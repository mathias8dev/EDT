package com.mathias8dev.edt.ui.screens.task

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import com.google.accompanist.pager.*
import com.mathias8dev.edt.domain.persistence.model.Category
import kotlinx.coroutines.launch




@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState, tabsList: List<Category>) {

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

        Tab(
            text = {
                Text(
                    "Toutes les tâches",
                    color = if (pagerState.currentPage == 0) Color.Black else Color.LightGray
                )
            },
            selected = pagerState.currentPage == 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        )

        tabsList.forEachIndexed { index, _ ->
            // on below line we are creating a tab.
            Tab(
                text = {
                    Text(
                        tabsList[index].title,
                        color = if (pagerState.currentPage == index + 1) Color.Black else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index + 1,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}
