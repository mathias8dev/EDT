package com.mathias8dev.edt.ui.screens.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground=true)
@Composable
fun TaskTopAppBar(count: Int = 0, onAddClicked: ()->Unit = {}, onCategoryManagerClicked: ()->Unit = {}, onDeleteTerminatedClicked: ()-> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(backgroundColor = Color.White, modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Tâches",
                modifier = Modifier.padding(start = 10.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .weight(2f)
            )
            IconButton(modifier = Modifier
                .size(24.dp)
                .padding(),
                onClick = { }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }

            IconButton(
                onClick = { expanded = true },
                content = { Icon(Icons.Default.MoreVert, contentDescription = "More") }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset((-16).dp, 0.dp),
            ) {
                DropdownMenuItem(onClick = { /* Handle menu item click */ }) {
                    Text("Gestions des catégories")
                }
                DropdownMenuItem(enabled = (count > 0) ,onClick = { /* Handle menu item click */ }) {
                    Text("Supprimer les tâches terminées${if (count == 0) "" else " ($count)"}", color = if (count > 0) Color.Black else Color.LightGray)
                }
            }


        }
    }
}
