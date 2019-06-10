package com.example.airlines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airlines.data.Customer
import com.example.airlines.data.Flight
import com.example.airlines.network.FlightApiService
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class CustomerListAdapter(context: Context): RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var customer: List<Customer> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val recyclerViewItem = inflater.inflate(R.layout.recycler_view_item,parent,false)

        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<List<Flight>> =
                FlightApiService.getInstance().getAllFlightsAsync().await()

            val noOfFlightsRetrived = response.body()?.size
            val count = 0
            while(noOfFlightsRetrived != 0){
                val flight = response.body()?.get(count)
                recyclerViewItem.name.text = flight?.flightPlane.toString()
                recyclerViewItem.number.text = flight?.flightNo.toString()
            }
        }
        return CustomerViewHolder(recyclerViewItem)
    }

    override fun getItemCount() = customer.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customer[position]
    }

    internal fun setCustomers(customers:List<Customer>) {
        this.customer = customers
        notifyDataSetChanged()
    }

   inner class CustomerViewHolder(itemVIew: View):RecyclerView.ViewHolder(itemVIew){

    }
}