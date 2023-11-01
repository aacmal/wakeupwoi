package com.acml.wakeupwoi.domain.repository

import com.acml.wakeupwoi.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    // Retrieve all alarms from the database
    fun getAllAlarms(): Flow<List<Alarm>>

    // Retrieve a single alarm from the database
    suspend fun getAlarm(id: Int): Alarm?

    // Insert an alarm to the database
    suspend fun insertAlarm(alarm: Alarm)

    // Update an alarm in the database
    suspend fun updateAlarm(alarm: Alarm)

    // Delete an alarm from the database
    suspend fun deleteAlarm(alarm: Alarm)
}