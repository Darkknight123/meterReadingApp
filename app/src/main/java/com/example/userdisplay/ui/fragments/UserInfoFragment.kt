package com.example.userdisplay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.userdisplay.R
import com.example.userdisplay.databinding.FragmentUserInfoBinding
import com.example.userdisplay.room.AppDatabase
import com.example.userdisplay.room.CustomerMeterReading
import com.example.userdisplay.room.CustomerMeterReadingDao
import com.example.userdisplay.room.MeterReadingViewModel
import com.example.userdisplay.ui.adapter.MeterReadingAdapter

class UserInfoFragment : Fragment() {

    private lateinit var binding : FragmentUserInfoBinding

    private lateinit var customerMeterReadingDao: CustomerMeterReadingDao

    private lateinit var meterReadingViewModel: MeterReadingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_info, container, false)

        // Initialize Room database and DAO
        val db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "customer_meter_reading.db").build()
        customerMeterReadingDao = db.meterReadingDao()

        // Create the adapter and RecyclerView
        val adapter = MeterReadingAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Observe the LiveData from the ViewModel and update the adapter
        meterReadingViewModel.allReadings.observe(viewLifecycleOwner) { readings ->
            readings?.let {
            }
        }

        setOnClickListeners()

        return binding.root
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
            customerMeterReadingDao.insert(customerMeterReading)

            // Clear input fields
            binding.clientsName.setText("")
            binding.clientsNumber.setText("")
            binding.meterReading.setText("")
        } catch (e:Exception) {
            throw e
        }
    }

}