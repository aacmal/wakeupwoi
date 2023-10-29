package com.acml.wakeupwoi.data

import android.content.Context

// app container for dependency injection
interface AppContainer {
    val alarmsRepository: AlarmsRepository
}

class AppDataContainer(private val content: Context): AppContainer{
    override val alarmsRepository: AlarmsRepository by lazy {
        val database = AlarmDatabase.getDatabase(content)
        OfflineAlarmsRepository(database.dao())
    }
}