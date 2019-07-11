package com.example.airlines

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.airlines.data.Flight
import com.example.airlines.databinding.FlightListRecyclerViewItemBinding

class FlightListAdapter(val context: Context): RecyclerView.Adapter<FlightListAdapter.FlightViewHolder>() {
    private var flights:List<Flight> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val inflater = LayoutInflater.from(context)
        val recyclerViewItem:FlightListRecyclerViewItemBinding = DataBindingUtil.inflate(inflater,R.layout.flight_list_recycler_view_item,parent,false)
        return FlightViewHolder(recyclerViewItem)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = flights[position]
        holder.binding.viewmodel = flight
    }

    override fun getItemCount(): Int {
        return flights.size
    }
    fun setFlight(flight:List<Flight>){
        flights = flight
        notifyDataSetChanged()
    }

    class FlightViewHolder(itemVIew: FlightListRecyclerViewItemBinding):RecyclerView.ViewHolder(itemVIew.root){
        val binding:FlightListRecyclerViewItemBinding = itemVIew
    }
}