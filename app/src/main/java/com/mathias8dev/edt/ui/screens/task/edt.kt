package com.mathias8dev.edt.ui.screens.task


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathias8dev.edt.domain.data.EdtItem
import com.mathias8dev.edt.domain.data.EdtItemTask
import com.mathias8dev.edt.ui.components.AnimatedVisibilityColumn
import com.mathias8dev.edt.ui.components.EdtPreviewer
import com.mathias8dev.edt.utils.Utils


@Preview(name = "Expandable")
@Composable
fun ExpandablePreview() {
    val state = EdtItem(
        0,
        6,
        30,
        7,
        10,
        "FPGA",
        "Je dois travailleur sur mon projet FPGA",
        listOf(
            EdtItemTask(0, "Lire un livre", true)
        )
    )


    EdtPreviewer {
        Column(modifier = Modifier.fillMaxSize()) {
            ExpandableEdtItem(
                state = state,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}


@Composable
fun ExpandableEdtItem(state: EdtItem, color: Color, modifier: Modifier) {

    val bottomActions = mutableListOf(
        ButtonItem(
            icon = Icons.Default.Edit,
            buttonText = "Modifier",
        ),
        ButtonItem(
            icon = Icons.Default.Edit,
            buttonText = "Ajouter tâche",
        ),
    )

    bottomActions.addAll(state.edtTasks.sortedBy { it.isDone }.map {
        ButtonItem(
            icon = null,
            buttonText = it.taskTitle,
            dataId = it.id,
            lineThrough = it.isDone,
            onClick = { id ->
                if (id != null) {
                    onEdtItemTaskItemClicked(id)
                }
            }
        )
    })


    val formattedDate = "${Utils.toColonSeparated(state.startHour, state.startMinute)} - ${
        Utils.toColonSeparated(
            state.endHour,
            state.endMinute
        )
    }"
    val expanded = remember { mutableStateOf(false) }


    // View

    Box(modifier = Modifier
        .background(color = color)
        .clickable { expanded.value = !expanded.value }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .then(
                    Modifier
                        .padding(10.dp)
                )

        ) {
            Text(
                text = Utils.toColonSeparated(state.startHour, state.endMinute),
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.Top)
                    .padding(top = 9.dp),
                textAlign = TextAlign.Center
            )

            Column {
                Text(
                    text = state.edtTitle, style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                AnimatedVisibilityColumn(visibility = expanded.value) {

                    Text(text = formattedDate)
                    Text(text = state.edtDescription)

                    Spacer(modifier = Modifier.height(10.dp))


                    LazyRow {
                        itemsIndexed(bottomActions) { _, item ->
                            Button(
                                onClick = { item.onClick(item.dataId) },
                                modifier = Modifier.height(36.dp)
                            ) {
                                item.icon?.let {
                                    Icon(
                                        item.icon,
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Text(
                                    text = item.buttonText, style = TextStyle(
                                        textDecoration = if (item.lineThrough) TextDecoration.LineThrough else null
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                    }

                }
            }

        }
    }
}

data class ButtonItem(
    val icon: ImageVector?,
    val buttonText: String,
    val lineThrough: Boolean = false,
    val dataId: Long? = null,
    val onClick: (Long?) -> Unit = {}
)

fun onEdtItemTaskItemClicked(taskId: Long) {}
