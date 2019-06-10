package com.example.airlines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airlines.data.Customer
import com.example.airlines.data.Flight
import com.example.airlines.network.CustomerApiService
import com.example.airlines.network.FlightApiService
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class FlightListAdapter(context: Context): RecyclerView.Adapter<FlightListAdapter.FlightViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val flights:List<Flight> = emptyList()

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = flights[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val recyclerViewItem = inflater.inflate(R.layout.recycler_view_item,parent,false)

        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<List<Customer>> =
                CustomerApiService.getInstance().getAllCustomersAsync().await()
        }

        return FlightViewHolder(recyclerViewItem)
    }

    override fun getItemCount(): Int {
        return flights.size
    }

    inner class FlightViewHolder(itemVIew: View):RecyclerView.ViewHolder(itemVIew){

    }
}