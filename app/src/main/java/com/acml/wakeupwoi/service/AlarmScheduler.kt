package com.acml.wakeupwoi.service

import com.acml.wakeupwoi.ui.screens.alarm.Alarm

interface AlarmScheduler {
    fun schedule(item: Alarm)
    fun cancel(item: Alarm)
}