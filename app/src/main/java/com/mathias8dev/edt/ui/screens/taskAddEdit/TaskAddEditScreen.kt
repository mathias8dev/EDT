package com.mathias8dev.edt.ui.screens.taskAddEdit

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mathias8dev.edt.domain.persistence.model.Category
import com.mathias8dev.edt.domain.persistence.model.Task
import com.mathias8dev.edt.domain.viewModel.CategoryViewModel
import com.mathias8dev.edt.domain.viewModel.TaskViewModel
import com.mathias8dev.edt.utils.Strings
import com.mathias8dev.edt.ui.components.EdtPreviewer
import com.mathias8dev.edt.utils.Utils
import org.joda.time.DateTime



@SuppressLint("UnrememberedMutableState")
@Composable
fun TaskAddEditScreen(
    modifier: Modifier = Modifier,
    taskId: Long? = null,
    onSaveClicked: () -> Unit = {},
    onCategorySelectorClicked: (Task) -> Unit = {},
    onPopScreen: () -> Unit = {},
    taskViewModel: TaskViewModel = viewModel(),
    categoryViewModel: CategoryViewModel = viewModel()
) {

    val task: MutableState<Task> = if (taskId != null) {
        taskViewModel.findTaskById(taskId)
        mutableStateOf(taskViewModel.foundTaskById.observeAsState(Task()).value ?: Task())
    } else remember {
        mutableStateOf(Task())
    }


    val taskCategory: State<Category?> = if (task.value.categoryId != null) {
        categoryViewModel.findById(task.value.categoryId!!)
        mutableStateOf(categoryViewModel.foundCategoryById.observeAsState().value)
    } else mutableStateOf(null)







    Column(modifier = modifier) {
        TaskAddEditTopAppBar(
            state = task.value.title,
            onStateChanged = { task.value = task.value.copy(title = it) },
            onSave = {
                if (taskViewModel.foundTaskById.value != null) taskViewModel.updateTask(task.value)
                else taskViewModel.insertTask(Task())
                onSaveClicked()
            })
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            /*TimeRemindSetup(state = timeRemindSetupState.value) {
                timeRemindSetupState.value = it
            }*/

            Spacer(modifier = Modifier.height(10.dp))
            TimeRemindSetup(
                state = TimeRemindSetupState(task.value.endingDateTime, task.value.remember),
                onStateChanged = {
                    task.value = task.value.copy(endingDateTime = it.datetime, remember = it.remind)
                }
            )
            CategorySelector(
                state = taskCategory.value,
                onClick = { onCategorySelectorClicked(task.value) })

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = task.value.info,
                onValueChange = { task.value = task.value.copy(info = it) },
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color.White),
                placeholder = { Text(Strings.info) }
            )

            if (taskViewModel.foundTaskById.value != null) Column {
                Spacer(modifier = Modifier.height(10.dp))

                TaskMarkAsDone()

                Spacer(modifier = Modifier.height(10.dp))

                TaskRemove(onRemoved = {
                    taskViewModel.deleteTask(task.value)
                    onPopScreen()
                })
            }
        }
    }

}


@Composable
fun TaskAddEditTopAppBar(
    state: String = "",
    onStateChanged: (String) -> Unit = {},
    onSave: (String) -> Unit
) {

    TopAppBar(backgroundColor = Color.White) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .padding(),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close button"
                )
            }

            // TExtField
            TextField(
                value = state,
                onValueChange = onStateChanged,
                modifier = Modifier
                    .background(Color.White)
                    .weight(1f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(Strings.title) }
            )
            // Save Button
            TextButton(
                onClick = { onSave(state) }
            ) {
                Text(Strings.save)
            }
        }
    }

}

@Composable
fun CategorySelector(
    modifier: Modifier = Modifier,
    state: Category? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.then(
            Modifier
                .clickable { }
                .fillMaxWidth()
                .height(50.dp)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Catégorie", style = TextStyle(
                    fontSize = 18.sp
                )
            )

            Text(
                text = state?.title ?: "Aucune", color = Color.Gray, style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
    }
}


@Composable
fun TaskMarkAsDone(
    modifier: Modifier = Modifier,
    state: Boolean = false,
    onStateChanged: (Boolean) -> Unit = {}
) {

    Card(
        modifier = modifier.then(
            Modifier
                .clickable { onStateChanged(!state) }
                .fillMaxWidth()
                .height(50.dp)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                color = Color.Blue,
                text = "Marquer comme terminée", style = TextStyle(
                    fontSize = 18.sp
                )
            )

            Checkbox(checked = state, onCheckedChange = { onStateChanged(it) })
        }
    }
}


@Composable
fun TaskRemove(modifier: Modifier = Modifier, onRemoved: () -> Unit = {}) {
    Card(
        modifier = modifier.then(
            Modifier
                .clickable { onRemoved() }
                .fillMaxWidth()
        )
    ) {
        Text(
            text = "Supprimer",
            color = Color.Red,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
        )

    }

}


@Composable
fun TimeRemindSetup(
    state: TimeRemindSetupState,
    onStateChanged: (TimeRemindSetupState) -> Unit = {}
) {

    val mContext = LocalContext.current

    val timePickerDialog = TimePickerDialog(
        mContext, { _, hour: Int, minute: Int ->
            onStateChanged(
                state.copy(
                    datetime = DateTime(
                        state.datetime.year,
                        state.datetime.monthOfYear,
                        state.datetime.dayOfMonth,
                        hour,
                        minute
                    )
                )
            )
        }, state.datetime.hourOfDay, state.datetime.minuteOfHour, true
    )

    val datePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, date: Int ->

            onStateChanged(
                state.copy(
                    datetime = DateTime(
                        year,
                        month,
                        date,
                        state.datetime.hourOfDay,
                        state.datetime.minuteOfHour
                    )
                )
            )
        }, state.datetime.year, state.datetime.monthOfYear, state.datetime.dayOfMonth
    )


    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Demain",
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.clickable { datePickerDialog.show() })
                Text(
                    text = Utils.toColonSeparated(
                        state.datetime.hourOfDay,
                        state.datetime.minuteOfHour
                    ),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.clickable { timePickerDialog.show() })
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Rappel",
                    style = TextStyle(fontSize = 18.sp),
                )
                Switch(
                    checked = state.remind,
                    onCheckedChange = { onStateChanged(state.copy(remind = it)) })
            }
        }
    }
}


data class TimeRemindSetupState(
    val datetime: DateTime,
    val remind: Boolean = false
)

@Preview
@Composable
fun TaskAddEditScreenPreview() {
    EdtPreviewer {
        TaskAddEditScreen()
    }
}