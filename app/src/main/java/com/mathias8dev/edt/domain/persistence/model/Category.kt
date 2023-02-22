package com.mathias8dev.edt.domain.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var title: String
)
