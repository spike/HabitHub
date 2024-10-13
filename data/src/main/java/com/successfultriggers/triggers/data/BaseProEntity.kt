package com.successfultriggers.triggers.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.successfultriggers.triggers.data.converter.Converters
import java.time.ZonedDateTime

@Entity(tableName = "basepro_table")
@TypeConverters(Converters::class)
data class BaseProEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val todoId: Int = 0,
    @ColumnInfo(name = "trigger") val trigger: String = "",
    @ColumnInfo(name = "desired_habit") val desiredHabit: String = "",

//    @ColumnInfo(name = "trigger_name") val triggerName: String = "",
//    @ColumnInfo(name = "desired_habit") val desiredHabit: String = "",
    @ColumnInfo(name = "minimal_action") val minimalAction: String = "",
    @ColumnInfo(name = "color") val color: Long = 0,
    @ColumnInfo(name = "last_updated") val lastUpdated: Long = System.currentTimeMillis() // Stores epoch milliseconds

    //@ColumnInfo(name = "date") val timestamp: kotlinx.datetime.LocalDateTime? = null,
    // kotlinx.datetime.Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    /*@ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lon") val lon: Double = 0.0,
    @ColumnInfo(name = "alarmOn") val alarmOn: Boolean = false,
    @ColumnInfo(name = "completed") val isCompleted: Boolean = false,
    @ColumnInfo(name = "image_path") val imgPath: Uri? = null,
    @ColumnInfo(name = "audio_path") val audioPath: Uri? = null*/

)

data class BaseProUpdate(
    val id: Int,
    //val alarmOn: Boolean
)