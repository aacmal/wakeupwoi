package com.acml.wakeupwoi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.acml.wakeupwoi.data.dao.AlarmDao
import com.acml.wakeupwoi.domain.model.Alarm

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Alarm::class], version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun dao(): AlarmDao

    companion object {
        @Volatile
        private var Instance: AlarmDatabase? = null

        fun getDatabase(context: Context): AlarmDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AlarmDatabase::class.java, "item_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}