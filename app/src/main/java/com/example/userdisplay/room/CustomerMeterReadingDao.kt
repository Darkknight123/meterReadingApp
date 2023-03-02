package com.example.userdisplay.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerMeterReadingDao {

    @Query("SELECT * FROM customer_meter_reading")
    fun getAll(): LiveData<CustomerMeterReading>

    @Insert
    fun insert(customerMeterReading: CustomerMeterReading)

    @Update
    fun update(customerMeterReading: CustomerMeterReading)

    @Delete
    fun delete(customerMeterReading: CustomerMeterReading)

}
