package com.acml.wakeupwoi.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acml.wakeupwoi.data.dao.AlarmDao
import com.acml.wakeupwoi.domain.model.Alarm

@Database(
    entities = [Alarm::class],
    version = 1,
    exportSchema = false
)
abstract class AlarmDb: RoomDatabase() {
    abstract val alarmDao: AlarmDao
}