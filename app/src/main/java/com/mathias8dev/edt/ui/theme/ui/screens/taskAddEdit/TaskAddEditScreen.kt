package com.mathias8dev.edt.ui.theme.ui.screens.taskAddEdit

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.mathias8dev.edt.ui.theme.ui.Strings
import com.mathias8dev.edt.ui.theme.ui.components.EdtPreviewer
import com.mathias8dev.edt.ui.theme.utils.Utils
import org.joda.time.DateTime


@Composable
fun TaskAddEditScreen(modifier: Modifier = Modifier, taskId: Long? = null) {

    /*val timeRemindSetupState = rememberSaveable {
        mutableStateOf(
            TimeRemindSetupState(DateTime())
        )
    }*/

    Column(modifier = modifier) {
        TaskAddEditTopAppBar {}
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

            CategorySelector {}

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .background(Color.White),
                placeholder = { Text(Strings.info) }
            )

            if (taskId != null) Column{
                Spacer(modifier = Modifier.height(10.dp))

                TaskMarAsDone()

                Spacer(modifier = Modifier.height(10.dp))

                TaskRemove()
            }
        }
    }

}


@Composable
fun TaskAddEditTopAppBar(state: String = "", onSave: (String) -> Unit) {

    var taskTitle: String by rememberSaveable { mutableStateOf("") }
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
                onValueChange = { taskTitle = it },
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
                onClick = { onSave(taskTitle) }
            ) {
                Text(Strings.save)
            }
        }
    }

}

@Composable
fun CategorySelector(modifier: Modifier = Modifier, onClick: (categoryId: Long) -> Unit = {}) {
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
                text = "Aucune", color = Color.Gray, style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
    }
}


@Composable
fun TaskMarAsDone(modifier: Modifier = Modifier, onMarkedAsDone: (Boolean) -> Unit = {}) {
    val checkState by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier.then(
            Modifier
                .clickable { onMarkedAsDone(checkState) }
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

            Checkbox(checked = checkState, onCheckedChange = { onMarkedAsDone(it) })
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
        Text(text = "Supprimer", color = Color.Red, textAlign = TextAlign.Start, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp))

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