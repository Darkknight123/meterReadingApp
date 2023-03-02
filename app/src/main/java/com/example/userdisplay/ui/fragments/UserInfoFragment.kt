package com.example.userdisplay.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.userdisplay.R
import com.example.userdisplay.databinding.FragmentUserInfoBinding
import com.example.userdisplay.room.*
import java.util.*

class UserInfoFragment : Fragment() {

    private lateinit var binding : FragmentUserInfoBinding

    private lateinit var customerMeterReadingDao: CustomerMeterReadingDao

    private lateinit var meterReadingViewModel: MeterReadingViewModel

    var appDatabase: AppDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_info, container, false)

        setOnClickListeners()

        loadDataFromRoom()

        return binding.root
    }
    /**
    * load group members
    */
    @SuppressLint("NotifyDataSetChanged")
    private fun loadDataFromRoom() {
        try {

        } catch (e: java.lang.Exception) {
            throw e

        }
    }

    private fun loadClientFromRoomDb(meterListeners: MeterListeners) {
        try {

            val handler = Handler()
            AppExecutors.instance!!.diskIO().execute {
                try {
                    if (getfamilyModelList() != null) {
                    }
                    handler.post { meterListeners.onResponse(200) }
                } catch (e: Exception) {
                    throw e
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * get  list from room db
     */
    private fun getfamilyModelList(): List<CustomerMeterReading?>? {
        return try {
            //get the fist questions stored in the db
            val modelList = Objects.requireNonNull(
                Objects.requireNonNull(appDatabase)
                    ?.familyDao()
            )!!.allUsers()
            if (modelList != null && modelList.isNotEmpty()) {
                modelList
            } else {
                null
            }
        } catch (e: Exception) {
            throw e
            null
        }
    }

    private fun setOnClickListeners() {
        try {
            // Set up click listener for save button
            binding.save.setOnClickListener {
                saveCustomerMeterReadingData()
            }
        } catch (e:Exception){
            throw e
        }
    }


    private fun saveCustomerMeterReadingData() {
        try {
            val customerName = binding.clientsName.text.toString()
            val customerNumber = binding.clientsNumber.text.toString()
            val meterReadings = binding.meterReading.text.toString().toIntOrNull()

            // Validate input
            if (customerName.isBlank() || customerNumber.isBlank() || meterReadings == null) {
                Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_SHORT).show()
                return
            }

            // Insert data into Room database
            val customerMeterReading = CustomerMeterReading(customerName, customerNumber, meterReadings, null)

            saveDataToRoom(customerMeterReading)

            // Clear input fields
            binding.clientsName.setText("")
            binding.clientsNumber.setText("")
            binding.meterReading.setText("")
        } catch (e:Exception) {
            throw e
        }
    }

    private fun saveDataToRoom(customerMeterReading: CustomerMeterReading) {
        try {

            AppExecutors.instance!!.diskIO().execute {
                try {
                    if (customerMeterReading != null) {
                        Objects.requireNonNull(
                            Objects.requireNonNull(
                                appDatabase
                            )!!.familyDao()
                        )?.insert(customerMeterReading)
                    }
                } catch (e: Exception) {
                    throw e
                }
            }


        } catch (e:Exception){
            throw e
        }
    }

}