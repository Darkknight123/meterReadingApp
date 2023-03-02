package com.example.userdisplay.room

import android.content.Context
import androidx.room.Room

class MeterReadingDatabaseHelper(context: Context) {

    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "meter_reading_database"
    ).build()

    fun getDatabase(): AppDatabase {
        return database
    }
}
