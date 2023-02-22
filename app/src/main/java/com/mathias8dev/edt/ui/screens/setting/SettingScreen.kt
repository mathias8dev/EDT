package com.mathias8dev.edt.ui.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun SettingScreen() {
    Column {
        TopAppBar {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }

                Text(text = "Paramètres", style = TextStyle(fontSize = 18.sp))
            }
        }

        Surface(
            modifier = Modifier.padding(
                10.dp
            )
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text("Nombre de semaines")
                        Text("Un", style = TextStyle(fontSize = 10.sp))
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                    Text("Jours du week-end", modifier = Modifier.padding(start = 10.dp))
                    Spacer(Modifier.weight(1f))
                    Switch(checked = false, onCheckedChange = {})
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text("Alertes")
                        Text("Non", style = TextStyle(fontSize = 10.sp))
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                    Text("Réinitialiser le planning", modifier = Modifier.padding(start = 10.dp))
                }

            }
        }
    }
}