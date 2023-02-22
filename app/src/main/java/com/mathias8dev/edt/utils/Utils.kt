package com.mathias8dev.edt.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

object Utils {

    fun toColonSeparated(hour: Int, minute: Int) =
        "${hour.padStart(2, '0')} : ${minute.padStart(2, '0')}"

    fun standardDatetimeFormat(dateTime: DateTime): String {
        val pattern = if (dateTime.year != DateTime.now().year) "d MMMM yyyy HH:mm" else "d MMMM HH:mm"
        val dateFormatter = DateTimeFormat.forPattern(pattern).withLocale(Locale.FRENCH)
        return dateFormatter.print(dateTime)
    }

    fun toStringOrEmpty(something: Any?): String = something?.toString() ?: ""

    fun daysFrom(weekendEnable: Boolean): List<String> {
        val days = mutableListOf("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi")
        if (weekendEnable) {
            days.add("Samedi")
            days.add("Dimanche")
        }

        return days
    }

    // I'm implement this function at this date, so it is my origin
    fun newEdtDateTime(): DateTime = DateTime(2023, 2,21, 21, 16, 34)


//    AppBarDefaults.TopAppBarElevation
//    MaterialTheme.typography.h5


}

fun Color.lighterVariant(): Color {
    val lighterColor = this.copy(alpha = 0.5f)
    /*val blendedColor = ColorUtils.blendARGB(this.toArgb(), lighterColor.toArgb(), 0.5f)
    return Color(blendedColor)*/
    return lighterColor
}

fun Color.lighterVariant(ratio: Float): Color {
    val lighterColor = this.copy(ratio)
    /*val blendedColor = ColorUtils.blendARGB(this.toArgb(), lighterColor.toArgb(), ratio)
    return Color(blendedColor)*/
    return lighterColor
}