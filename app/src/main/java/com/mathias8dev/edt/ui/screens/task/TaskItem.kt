package com.mathias8dev.edt.ui.screens.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathias8dev.edt.ui.theme.EDTTheme
import com.mathias8dev.edt.domain.data.TaskItemState
import com.mathias8dev.edt.utils.Strings
import com.mathias8dev.edt.ui.components.AnimatedVisibilityColumn
import com.mathias8dev.edt.utils.Utils
import com.mathias8dev.edt.utils.lighterVariant
import org.joda.time.DateTime


@Composable
fun TaskItem(
    state: TaskItemState,
    modifier: Modifier = Modifier,
    onEditClicked: (Long) -> Unit = {},
    onDeleteClicked: (Long) -> Unit = {},
    onStateChanged: (TaskItemState) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val taskState = if (state.isTerminated)
        "Terminée - ${Utils.toStringOrEmpty(state.taskCategory)}"
    else {
        if (state.taskEndingDateTime.isBeforeNow) "En retard - ${Utils.toStringOrEmpty(state.taskCategory)}"
        else "${Utils.standardDatetimeFormat(state.taskEndingDateTime)} - ${
            Utils.toStringOrEmpty(
                state.taskCategory
            )
        }"
    }
    val lineThrough = if (state.isTerminated) TextDecoration.LineThrough else null
    val color = if (state.isTerminated) Color.LightGray else Color.White

    Surface(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }),
        color = Color(state.color)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(10.dp)
            ) {
                Text(state.taskTitle, style = TextStyle(fontSize = 22.sp, color = color), textDecoration = lineThrough)
                Spacer(modifier = Modifier.height(10.dp))
                Text(taskState, style = TextStyle(fontSize = 14.sp, color = color))

                AnimatedVisibilityColumn(visibility = expanded) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        Utils.standardDatetimeFormat(state.taskEndingDateTime),
                        style = TextStyle(fontSize = 14.sp, color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row {
                        Button(onClick = { onEditClicked(state.taskId) }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(state.color).lighterVariant(0.1f))) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                            Text(Strings.edit)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = { onDeleteClicked(state.taskId) }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(state.color).lighterVariant(0.1f))) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                            Text(Strings.delete)
                        }
                    }
                }
            }



            Checkbox(
                checked = state.isTerminated,
                onCheckedChange = { onStateChanged(state.copy(isTerminated = it)) },
                modifier = Modifier.padding(start=20.dp)
            )
        }
    }
}



@Preview(name = "taskItem", showBackground = true)
@Composable
fun TaskItemPreview() {
    var taskItemState by remember {
        mutableStateOf(TaskItemState(
            0,
            "Mathias",
            false,
            "Jetpack Compose",
            DateTime.now().apply { minusYears(1) }
        ))
    }
    EDTTheme {
        TaskItem(taskItemState, onStateChanged = {
            taskItemState = it
        })
    }
}