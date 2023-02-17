package com.mathias8dev.edt.ui.theme.domain.data

data class EdtItem(
    val id: Long,
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int,
    val edtTitle: String,
    val edtDescription: String,
    val edtTasks: List<EdtItemTask> = emptyList()
)