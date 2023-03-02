package com.example.userdisplay.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface CustomerMeterReadingDao {
    @Query("SELECT * FROM customer_meter_reading")
    fun getAll(): Flow<CustomerMeterReading>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(familyMember: CustomerMeterReading)

    @Delete
    fun delete(familyMember: CustomerMeterReading)

    @Query("SELECT * FROM customer_meter_reading")
    fun allUsers(): List<CustomerMeterReading?>?

    @Update
    fun update(familyMember: CustomerMeterReading)
}