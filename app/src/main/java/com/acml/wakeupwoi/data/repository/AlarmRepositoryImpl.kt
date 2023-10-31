package com.acml.wakeupwoi.data.repository

import com.acml.wakeupwoi.data.dao.AlarmDao
import com.acml.wakeupwoi.domain.model.Alarm
import com.acml.wakeupwoi.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow

class AlarmRepositoryImpl(private val alarmDao: AlarmDao): AlarmRepository {
    override fun getAllAlarms(): Flow<List<Alarm>> = alarmDao.getAll()

    override fun getAlarm(id: Int): Flow<Alarm?> = alarmDao.get(id)

    override suspend fun insertAlarm(alarm: Alarm) = alarmDao.insert(alarm)

    override suspend fun updateAlarm(alarm: Alarm) = alarmDao.update(alarm)

    override suspend fun deleteAlarm(alarm: Alarm) = alarmDao.delete(alarm)
}