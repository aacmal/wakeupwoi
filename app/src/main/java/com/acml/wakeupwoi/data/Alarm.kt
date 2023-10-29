package com.acml.wakeupwoi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time: String,
    val label: String,
    val isActive: Boolean,
    val isRepeat: Boolean,
)
