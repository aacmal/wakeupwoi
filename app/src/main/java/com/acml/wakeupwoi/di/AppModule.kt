package com.acml.wakeupwoi.di

import android.content.Context
import androidx.room.Room
import com.acml.wakeupwoi.code.Constants.Companion.ALARM_TABLE
import com.acml.wakeupwoi.data.dao.AlarmDao
import com.acml.wakeupwoi.data.network.AlarmDb
import com.acml.wakeupwoi.data.repository.AlarmRepositoryImpl
import com.acml.wakeupwoi.domain.repository.AlarmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideAlarmDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        AlarmDb::class.java,
        ALARM_TABLE
    ).build()

    @Provides
    fun provideAlarmDao(
        alarmDb: AlarmDb
    ) = alarmDb.alarmDao

    @Provides
    fun provideAlarmRepository(
        alarmDao: AlarmDao
    ): AlarmRepository = AlarmRepositoryImpl(
        alarmDao = alarmDao
    )
}