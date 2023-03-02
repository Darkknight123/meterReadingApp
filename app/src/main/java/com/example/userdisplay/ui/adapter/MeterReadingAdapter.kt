package com.example.userdisplay.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userdisplay.R
import com.example.userdisplay.room.CustomerMeterReading

class MeterReadingAdapter: ListAdapter<CustomerMeterReading, MeterReadingAdapter.MeterReadingViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeterReadingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meter_reading, parent, false)
        return MeterReadingViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeterReadingViewHolder, position: Int) {
        val meterReading = getItem(position)

        holder.customerName.text = meterReading.customerName
        holder.customerNumber.text = meterReading.customerNumber
        holder.meterReading.text = meterReading.meterReadings.toString()

        if (meterReading.meterReadings < 0) {
            holder.dontBill.visibility = View.VISIBLE
        } else {
            holder.dontBill.visibility = View.GONE
        }
    }


    inner class MeterReadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customerName: TextView = itemView.findViewById(R.id.clientsName)
        val customerNumber: TextView = itemView.findViewById(R.id.clientsNumber)
        val meterReading: TextView = itemView.findViewById(R.id.meterReading)
        val dontBill: TextView = itemView.findViewById(R.id.dontBill)
    }
    class DiffCallback : DiffUtil.ItemCallback<CustomerMeterReading>() {
        override fun areItemsTheSame(oldItem: CustomerMeterReading, newItem: CustomerMeterReading) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CustomerMeterReading, newItem: CustomerMeterReading) = oldItem == newItem
    }
}
