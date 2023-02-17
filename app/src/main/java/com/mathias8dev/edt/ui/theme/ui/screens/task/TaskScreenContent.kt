package com.mathias8dev.edt.ui.theme.ui.screens.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mathias8dev.edt.ui.theme.greenColor

@Composable
fun TaskScreenContent(data: String) {
    // on below line we are creating a column
    Column(
        // in this column we are specifying modifier
        // and aligning it center of the screen on below lines.
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // in this column we are specifying the text
        Text(
            // on below line we are specifying the text message
            text = data,

            // on below line we are specifying the text style.
            style = MaterialTheme.typography.h5,

            // on below line we are specifying the text color
            color = greenColor,

            // on below line we are specifying the font weight
            fontWeight = FontWeight.Bold,

            //on below line we are specifying the text alignment.
            textAlign = TextAlign.Center
        )
    }
}
