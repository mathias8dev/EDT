package com.mathias8dev.edt.ui.theme.ui.screens.edtAdd

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.mathias8dev.edt.ui.theme.greenColor
import com.mathias8dev.edt.ui.theme.ui.Strings


@Preview
@Composable
fun EdtAddScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        var edtText: String by rememberSaveable { mutableStateOf("") }

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
                    value = edtText,
                    onValueChange = { edtText = it },
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
                    onClick = {}
                ) {
                    Text(Strings.save)
                }
            }
        }

        TimeGapChooser(
            startHour = 22,
            startMinute = 30,
            endHour = 0,
            endMinute = 1
        ) { _startHour, _startMinute, _endHour, _endMinute ->

        }

        Button(
            onClick = {

            },
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = greenColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(Strings.chooseAColor, color=Color.White, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())
        }

        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.White),
            placeholder = { Text(Strings.info) }
        )

    }
}

@Composable
fun TimeGapChooser(
    startHour: Int,
    startMinute: Int,
    endHour: Int,
    endMinute: Int,
    onGapChanged: (startHour: Int, startMinute: Int, endHour: Int, endMinute: Int) -> Unit
) {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            TimeElement(
                name = Strings.start,
                hour = startHour,
                minute = startMinute
            ) { _hour, _minute ->
                onGapChanged(
                    _hour,
                    _minute,
                    endHour,
                    endMinute
                )
            }
            TimeElement(name = Strings.end, hour = endHour, minute = endMinute) { _hour, _minute ->
                onGapChanged(
                    startHour,
                    startMinute,
                    _hour,
                    _minute
                )
            }
            Spacer(modifier = Modifier.width(20.dp))

        }
    }
}

@Composable
fun TimeElement(
    name: String,
    hour: Int,
    minute: Int,
    onStateChanged: (hour: Int, minute: Int) -> Unit
) {

    val mContext = LocalContext.current

    val timePickerDialog = TimePickerDialog(
        mContext, { _, _hour: Int, _minute: Int ->
            onStateChanged(_hour, _minute)
        }, hour, minute, false
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(name)
        Text(
            "$hour:$minute",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable { timePickerDialog.show() })
    }
}

