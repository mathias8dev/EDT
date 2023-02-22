package com.mathias8dev.edt.ui.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
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
import com.mathias8dev.edt.utils.lighterVariant


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
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}


@Composable
fun ExpandableEdtItem(
    state: EdtItem,
    modifier: Modifier,
    onAddTaskClicked: (Long) -> Unit = {},
    onModifyEdtClicked: (Long) -> Unit = {},
    onTaskClicked: (Long) -> Unit = {}
) {

    val bottomActions = mutableListOf(
        ButtonItem(
            icon = Icons.Default.Edit,
            buttonText = "Modifier",
            onClick = { onModifyEdtClicked(state.id) }
        ),
        ButtonItem(
            icon = Icons.Default.Edit,
            buttonText = "Ajouter tâche",
            onClick = { onModifyEdtClicked(state.id) }
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
                    onTaskClicked(id)
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
    val edtItemColor = Color(state.color)
    // View

    Box(modifier = Modifier
        .background(color = edtItemColor)
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
                color = Color.White,
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.Top)
                    .padding(top = 9.dp),
                textAlign = TextAlign.Center,
            )

            Column {
                Text(
                    text = state.edtTitle, style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                )

                AnimatedVisibilityColumn(visibility = expanded.value) {

                    Text(
                        text = formattedDate,
                        color = Color.White,
                    )
                    Text(
                        text = state.edtDescription,
                        color = Color.White,
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    LazyRow {
                        itemsIndexed(bottomActions) { _, item ->
                            Button(
                                onClick = { item.onClick(item.dataId) },
                                modifier = Modifier.height(36.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = edtItemColor.lighterVariant(),
                                    contentColor = Color.White,
                                )
                            ) {
                                item.icon?.let {
                                    Icon(
                                        item.icon,
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp),

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
