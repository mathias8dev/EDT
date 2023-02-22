package com.mathias8dev.edt.domain.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mathias8dev.edt.domain.persistence.DateTimeTypeConverter
import com.mathias8dev.edt.utils.ColorUtils
import org.joda.time.DateTime

@Entity("tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo("category_id")
    var categoryId: Long? = null,
    var title: String = "",
    @ColumnInfo("ending_datetime")
    var endingDateTime: DateTime = DateTime.now().apply { plusDays(1) },
    var remember: Boolean = false,
    @ColumnInfo("is_terminated")
    var isTerminated: Boolean = false,
    var info: String = "",
    var color: Long = ColorUtils().randomColor
)
