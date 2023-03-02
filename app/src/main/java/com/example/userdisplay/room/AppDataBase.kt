package com.example.userdisplay.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.coroutines.CoroutineContext


@Database(entities = [CustomerMeterReading::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun meterReadingDao(): CustomerMeterReadingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "meter_reading_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
