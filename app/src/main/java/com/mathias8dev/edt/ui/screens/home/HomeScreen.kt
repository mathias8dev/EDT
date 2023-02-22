package com.mathias8dev.edt.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mathias8dev.edt.domain.viewModel.EdtViewModel
import com.mathias8dev.edt.ui.components.EdtPreviewer
import com.mathias8dev.edt.utils.PrefsManager
import com.mathias8dev.edt.utils.Utils
import com.mathias8dev.edt.utils.dataStore
import kotlinx.coroutines.flow.map


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(edtViewModel: EdtViewModel = viewModel() ) {

    val pagerState = rememberPagerState()
    val prefsManager = PrefsManager(LocalContext.current.dataStore)
    val weekName = prefsManager.weekName.asLiveData().observeAsState("Un")
    val weekendEnable = prefsManager.weekendEnable.asLiveData().observeAsState(false)
    val days = Utils.daysFrom(weekendEnable.value)
    edtViewModel.getAllEdtsWithTasksByDayNameAndWeekName(days[pagerState.currentPage], weekName.value)
    val homeState = edtViewModel.foundEdtAndTasks.observeAsState(emptyList())

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        HomeScreenTopAppBar()
        Tabs(pagerState = pagerState, tabs=days)
        TabsContent(pagerState = pagerState, pagesCount = days.size, state = homeState.value, {}, {}, {})
    }
}

@Composable
fun HomeScreenTopAppBar(onAddClicked: ()->Unit = {}, onOptionsClicked: ()->Unit = {}) {
    TopAppBar() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("TimeTable", style=MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.weight(2f))
            IconButton(onClick = onAddClicked) {
                Icon(Icons.Default.Add, contentDescription="")
            }

            IconButton(onClick = onOptionsClicked) {
                Icon(Icons.Default.MoreVert, contentDescription = "")
            }
        }
    }
}


@Preview(showBackground = true, name = "HomeScreen", showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EdtPreviewer {
        HomeScreen()
    }
}