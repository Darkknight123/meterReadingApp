package com.example.userdisplay.room

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MeterReadingViewModel(private val repository: MeterReadingRepository) : ViewModel() {


    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(model: CustomerMeterReading) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(model)
    }

}

