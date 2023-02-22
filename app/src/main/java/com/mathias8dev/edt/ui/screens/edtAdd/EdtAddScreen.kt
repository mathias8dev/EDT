package com.mathias8dev.edt.ui.screens.edtAdd

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mathias8dev.edt.domain.data.EdtItem
import com.mathias8dev.edt.domain.viewModel.EdtColorsViewModel
import com.mathias8dev.edt.utils.Strings
import org.joda.time.LocalDateTime


@Preview
@Composable
fun EdtAddScreen(
    onSelectColorButtonClicked: () -> Unit = {},
    onSaveButtonClicked: (EdtItem) -> Unit = {},
    edtColorsViewModel: EdtColorsViewModel = viewModel()
) {
    val currentDateTime = LocalDateTime.now()

    var startHour by remember { mutableStateOf(currentDateTime.hourOfDay) }
    var startMinute by remember { mutableStateOf(currentDateTime.minuteOfHour) }
    var endHour by remember { mutableStateOf(currentDateTime.plusHours(1).hourOfDay) }
    var endMinute by remember { mutableStateOf(startMinute) }
    var edtTitle: String by rememberSaveable { mutableStateOf("") }
    var edtDescription: String by rememberSaveable { mutableStateOf("") }
    val edtColor = edtColorsViewModel.defaultColor.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

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
                    value = edtTitle,
                    onValueChange = { edtTitle = it },
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
                    onClick = {
                        onSaveButtonClicked(
                            EdtItem(
                                0,
                                startHour,
                                startMinute,
                                endHour,
                                endMinute,
                                edtTitle,
                                edtDescription,
                                color = edtColor.value.toLong()
                            )
                        )
                    }
                ) {
                    Text(Strings.save)
                }
            }
        }

        TimeGapChooser(
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute
        ) { _startHour, _startMinute, _endHour, _endMinute ->
            startHour = _startHour
            startMinute = _startMinute
            endHour = _endHour
            endMinute = _endMinute
        }

        Button(
            onClick = onSelectColorButtonClicked,
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = edtColor ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                Strings.chooseAColor,
                color = Color.White,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }

        TextField(
            value = edtDescription,
            onValueChange = {edtDescription = it},
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
            TimeElement(
                name = Strings.start,
                hour = startHour,
                minute = startMinute,
                modifier = Modifier.weight(1f)
            ) { _hour, _minute ->
                onGapChanged(
                    _hour,
                    _minute,
                    endHour,
                    endMinute
                )
            }
            TimeElement(
                name = Strings.end,
                hour = endHour,
                minute = endMinute,
                modifier = Modifier.weight(1f)
            ) { _hour, _minute ->
                onGapChanged(
                    startHour,
                    startMinute,
                    _hour,
                    _minute
                )
            }

        }
    }
}

@Composable
fun TimeElement(
    name: String,
    hour: Int,
    minute: Int,
    modifier: Modifier = Modifier,
    onStateChanged: (hour: Int, minute: Int) -> Unit
) {

    val mContext = LocalContext.current

    val timePickerDialog = TimePickerDialog(
        mContext, { _, _hour: Int, _minute: Int ->
            onStateChanged(_hour, _minute)
        }, hour, minute, false
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
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

