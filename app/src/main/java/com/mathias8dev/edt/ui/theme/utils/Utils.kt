package com.mathias8dev.edt.ui.theme.utils

object Utils {
    fun toColonSeparated(hour: Int, minute: Int) =
        "${hour.padStart(2, '0')} : ${minute.padStart(2, '0')}"
}