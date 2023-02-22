package com.mathias8dev.edt.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mathias8dev.edt.R
import com.mathias8dev.edt.domain.data.EdtItem
import com.mathias8dev.edt.domain.data.EdtItemTask
import com.mathias8dev.edt.domain.persistence.model.EdtAndTask
import com.mathias8dev.edt.domain.viewModel.EdtViewModel
import com.mathias8dev.edt.ui.components.EdtPreviewer

@Composable
fun HomeScreenContent(
    state: List<EdtAndTask>,
    onAddTaskClicked: (Long) -> Unit,
    onModifyEdtClicked: (Long) -> Unit,
    onTaskClicked: (Long) -> Unit
) {
    val edtItems = state.map { EdtItem.fromEdtAndTask(it) }
    if (edtItems.isEmpty()) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_calendar_today_24),
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Aucun évènement",
            style = TextStyle(color = Color.Gray, fontSize = 20.sp)
        )
    } else {
        LazyColumn {
            items(edtItems.size, key = { edtItems[it] }) { index ->
                ExpandableEdtItem(
                    state = edtItems[index],
                    onTaskClicked = onTaskClicked,
                    onAddTaskClicked = onAddTaskClicked,
                    onModifyEdtClicked = onModifyEdtClicked,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

