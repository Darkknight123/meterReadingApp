package com.example.userdisplay.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_meter_reading")
data class CustomerMeterReading(
    @ColumnInfo(name = "customer_name") val customerName: String,
    @ColumnInfo(name = "customer_number") val customerNumber: String,
    @ColumnInfo(name = "meter_readings") val meterReadings: Int,
    @ColumnInfo(name = "meter_image") val meterImage: ByteArray?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
