package com.mathias8dev.edt.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mathias8dev.edt.domain.data.TaskItemState
import com.mathias8dev.edt.domain.viewModel.CategoryViewModel
import com.mathias8dev.edt.domain.viewModel.TaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Preview(name = "taskScreen", showBackground = true)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TaskScreen(
    onTaskEditClicked: (Long) -> Unit = {},
    onAddTaskClicked: (Long?) -> Unit = {},
    onCategoryManagerClicked: () -> Unit = {},
    taskViewModel: TaskViewModel = viewModel(),
    categoryViewModel: CategoryViewModel = viewModel()
) {

    val pagerState = rememberPagerState()

    val categories = categoryViewModel.allCategories.observeAsState().value ?: emptyList()
    val tasks = if (pagerState.currentPage == 0)
        taskViewModel.allTasks.observeAsState().value?.map {
            TaskItemState.newFromTask(it)
        }?.sortedBy { it.isTerminated } ?: emptyList()
    else {
        val category = categories[pagerState.currentPage - 1]
        taskViewModel.getTasksByCategoryId(category.id)
        taskViewModel.allTasksByCategoryId
            .observeAsState().value?.map {
                TaskItemState.newFromTask(it, category.title)
            } ?: emptyList()
    }


    Column(
        modifier = Modifier.background(Color.White)
    ) {
        TaskTopAppBar(
            count = categories.size,
            onAddClicked = { onAddTaskClicked(if (pagerState.currentPage == 0) null else categories[pagerState.currentPage - 1].id) },
            onCategoryManagerClicked = { onCategoryManagerClicked() },
            onDeleteTerminatedClicked = {
                taskViewModel.deleteTerminatedTasks()
                taskViewModel.getAllTasks()
            })
        Tabs(pagerState = pagerState, tabsList = categories)
        HorizontalPager(state = pagerState, count = categories.size + 1) { page ->

            TaskScreenContent(
                isFirstTab = page == 0,
                tasks = tasks,
                onDeleteClicked = {
                    taskViewModel.deleteTaskById(it)
                    taskViewModel.getAllTasks()
                },
                onEditClicked = {
                    onTaskEditClicked(it)
                    taskViewModel.getAllTasks()
                },
                onStateChanged = {
                    taskViewModel.viewModelScope.launch(Dispatchers.IO) {
                        val task = taskViewModel.foundTaskById(it.taskId)
                        if (task != null) {
                            task.isTerminated = !it.isTerminated
                            taskViewModel.updateTask(task)
                            taskViewModel.getAllTasks()
                        }
                    }
                },
                onCategoryRemovedClicked = {
                    if (categories.isNotEmpty()) {
                        categoryViewModel.deleteCategory(categories[pagerState.currentPage - 1])
                        categoryViewModel.getAllCategories()
                        taskViewModel.getAllTasks()
                    }
                })

        }
    }
}

