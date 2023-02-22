package com.mathias8dev.edt.domain.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


@ProvidedTypeConverter
class DateTimeTypeConverter {

    private val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    @TypeConverter
    fun fromTimestamp(value: String?): DateTime? {
        return if (value != null) {
            formatter.parseDateTime(value)
        } else {
            null
        }
    }

    @TypeConverter
    fun toTimestamp(dateTime: DateTime?): String? {
        return dateTime?.toString(formatter)
    }
}