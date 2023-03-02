package com.example.userdisplay.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class MeterReadingRepository(private val meterReadingDao: CustomerMeterReadingDao) {
    val response : kotlinx.coroutines.flow.Flow<CustomerMeterReading> = meterReadingDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(model: CustomerMeterReading){
        meterReadingDao.insert(model)
    }
}