package com.example.airlines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airlines.data.Customer

class CustomerListAdapter(context: Context): RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var customer: List<Customer> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val recyclerViewItem = inflater.inflate(R.layout.flight_list_recycler_view_item,parent,false)
//        recyclerViewItem.name.text = flight?.flightPlanes.toString()
//        recyclerViewItem.number.text = flight?.flightNo.toString()
        return CustomerViewHolder(recyclerViewItem)
    }

    override fun getItemCount() = customer.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        //val customer = customer[position]
    }

    internal fun setCustomers(customers:List<Customer>) {
        this.customer = customers
        notifyDataSetChanged()
    }

   inner class CustomerViewHolder(itemVIew: View):RecyclerView.ViewHolder(itemVIew){

    }
}