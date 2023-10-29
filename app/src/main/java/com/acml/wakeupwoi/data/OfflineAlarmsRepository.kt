package com.acml.wakeupwoi.data

import kotlinx.coroutines.flow.Flow

class OfflineAlarmsRepository(private val alarmDao: AlarmDao): AlarmsRepository{
    override fun getAllAlarms(): Flow<List<Alarm>> = alarmDao.getAll()

    override fun getAlarm(id: Int): Flow<Alarm?> = alarmDao.get(id)

    override suspend fun insertAlarm(alarm: Alarm) = alarmDao.insert(alarm)

    override suspend fun updateAlarm(alarm: Alarm) = alarmDao.update(alarm)

    override suspend fun deleteAlarm(alarm: Alarm) = alarmDao.delete(alarm)
}