package com.acml.wakeupwoi.ui.screens.alarm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.UUID


data class Alarm(
    val id: UUID = UUID.randomUUID(),
    val hour: Int,
    val minute: Int,
    val isActive: Boolean,
    val label: String
)

class AlarmViewModel : ViewModel() {
    private var alarms = mutableStateListOf<Alarm>(
        Alarm(
            id = UUID.randomUUID(),
            hour = 12,
            minute = 0,
            isActive = false, label = "Alarm 1"
        ),
    )

    fun getAlarms(): List<Alarm> {
        return alarms
    }

    fun addAlarm(alarm: Alarm) {
        alarms.add(alarm)
    }

    fun removeAlarm(alarm: Alarm) {
        alarms.remove(alarm)
    }

    fun updateAlarm(alarm: Alarm) {
        val index = alarms.indexOfFirst { it.id == alarm.id }
        alarms[index] = alarm
    }
}