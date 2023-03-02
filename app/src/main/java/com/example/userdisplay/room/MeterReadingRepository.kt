package com.example.userdisplay.room

import androidx.lifecycle.LiveData

class MeterReadingRepository(private val meterReadingDao: CustomerMeterReadingDao) {

    val allReadings: LiveData<CustomerMeterReading> = meterReadingDao.getAll()

    fun insert(meterReading: CustomerMeterReading) {
        meterReadingDao.insert(meterReading)
    }

    fun delete(meterReading: CustomerMeterReading) {
        meterReadingDao.delete(meterReading)
    }
}
