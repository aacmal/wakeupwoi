package com.acml.wakeupwoi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acml.wakeupwoi.code.Constants.Companion.ALARM_TABLE

@Entity(tableName = ALARM_TABLE)
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hour: Int,
    val minute: Int,
    val label: String,
    val isActive: Boolean,
    val isRepeat: Boolean,
)
