package com.example.userdisplay.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MeterReadingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MeterReadingRepository

    val allReadings: LiveData<CustomerMeterReading>

    init {
        val meterReadingDao = AppDatabase.getDatabase(application).meterReadingDao()
        repository = MeterReadingRepository(meterReadingDao)
        allReadings = repository.allReadings
    }

    fun insert(meterReading: CustomerMeterReading) = viewModelScope.launch {
        repository.insert(meterReading)
    }
}

