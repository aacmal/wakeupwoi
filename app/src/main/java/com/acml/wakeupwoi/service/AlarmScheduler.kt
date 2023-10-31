package com.acml.wakeupwoi.service

import com.acml.wakeupwoi.domain.model.Alarm


interface AlarmScheduler {
    fun schedule(item: Alarm)
    fun cancel(item: Alarm)
}