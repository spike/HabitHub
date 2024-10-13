package com.successfultriggers.triggers.data.converter

import android.net.Uri
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime


class Converters {

    @TypeConverter
    fun fromZonedDateTime(zonedDateTime: ZonedDateTime?): Long? {
        return zonedDateTime?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun toZonedDateTime(epochMillis: Long?): ZonedDateTime? {
        return epochMillis?.let {
            ZonedDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
        }
    }

    @TypeConverter
    fun fromString(value: String?): Uri? {
        return if (value == null) null else Uri.parse(value)
    }

    @TypeConverter
    fun toString(uri: Uri?): String? {
        return uri?.toString()
    }
}
