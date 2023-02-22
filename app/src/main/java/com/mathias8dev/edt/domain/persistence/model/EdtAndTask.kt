package com.mathias8dev.edt.domain.persistence.model

data class EdtAndTask(
    val edt: Edt,
    val tasks: List<Task>
)
