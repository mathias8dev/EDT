package com.mathias8dev.edt.ui.screens.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathias8dev.edt.R
import com.mathias8dev.edt.domain.data.TaskItemState

@Preview(showBackground = true)
@Composable
fun TaskScreenContent(
    isFirstTab: Boolean = true,
    tasks: List<TaskItemState> = emptyList(),
    onEditClicked: (Long) -> Unit = {},
    onDeleteClicked: (Long) -> Unit = {},
    onCategoryRemovedClicked: () -> Unit = {},
    onStateChanged: (TaskItemState) -> Unit = {}
) {
    // on below line we are creating a column
    if (tasks.isEmpty())
        Column(
            // in this column we are specifying modifier
            // and aligning it center of the screen on below lines.
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // in this column we are specifying the text
            Icon(
                painter = painterResource(id = R.drawable.baseline_calendar_today_24),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.size(72.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Aucune tâche",
                style = TextStyle(color = Color.Gray, fontSize = 20.sp)
            )
            if (!isFirstTab) {
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onCategoryRemovedClicked,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) {
                    Text(
                        text = "Supprimer la catégorie",
                        style = TextStyle(color = Color.White, fontSize = 20.sp)
                    )
                }
            }
        }
    else LazyColumn {
        items(tasks.size, key = { tasks[it].taskId }) {
            TaskItem(state = tasks[it])
        }
    }
}
