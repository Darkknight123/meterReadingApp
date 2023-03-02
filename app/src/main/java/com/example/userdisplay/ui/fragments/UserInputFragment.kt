package com.example.userdisplay.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.userdisplay.R
import com.example.userdisplay.databinding.FragmentUserInputBinding


class UserInputFragment : Fragment() {
    private lateinit var binding: FragmentUserInputBinding

    private var registrationNumbers = ArrayList<Int>()
    private lateinit var adapter: ArrayAdapter<Int>
    private lateinit var listView: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_input, container, false)

        setAdapter()

        setOnClickListeners()

        return binding.root
    }

    private fun setOnClickListeners() {
        try {
            binding.addValue.setOnClickListener {
                addRegistrationNumber(view)
            }
            binding.addUserDetail.setOnClickListener {
                NavHostFragment.findNavController(this).navigate(
                    R.id.action_userInputFragment_to_userInfoFragment2
                )
            }
        } catch (e:Exception){
            throw e
        }
    }
    fun addRegistrationNumber(view: View?) {
        val input = binding.registrationNumber.text.toString()
        try {
            val number = input.toInt()
            if (!registrationNumbers.contains(number)) {
                registrationNumbers.add(number)
                adapter.notifyDataSetChanged()
            }
            var numbers: ArrayList<Int?>? = ArrayList()

             removeDuplicates(numbers)

            Log.d("UserInPutFragment", "Numbers: " + numbers.toString())

        } catch (e: NumberFormatException) {
            Toast.makeText(requireActivity(), "Invalid input", Toast.LENGTH_SHORT).show()
        }
        binding.registrationNumber.setText("")
    }

    fun removeDuplicates(numbers: ArrayList<Int?>?): ArrayList<Int?>? {
        val result: ArrayList<Int?>? = ArrayList()
        val set: HashSet<Int> = HashSet()
        for (number in numbers!!) {
            if (set.contains(number)) {
                continue
            }
            set.add(number!!)
            result!!.add(number)
        }
        if (result!!.size === 0) {
            result!!.add(-1)
        }
        return result
    }

    private fun setAdapter() {
        try {
            registrationNumbers = ArrayList()
            adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                registrationNumbers
            )
            listView = binding.listView
            listView.adapter = adapter
        } catch (e: Exception) {
            throw e
        }
    }

    private fun init() {

    }

}