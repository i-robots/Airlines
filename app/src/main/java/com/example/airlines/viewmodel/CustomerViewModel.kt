package com.example.airlines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.airlines.data.Customer
import com.example.airlines.data.AppDatabase
import com.example.airlines.repository.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application):AndroidViewModel(application) {
    private val customerRepository: CustomerRepository
    val allCustomer: LiveData<List<Customer>>

    init {
        val customerDao = AppDatabase.getDatabase(application).customerDao()
        customerRepository = CustomerRepository(customerDao)
        allCustomer = customerRepository.allCustomers()
    }

    fun insertCustomer(customer: Customer) = viewModelScope.launch(Dispatchers.IO){
        customerRepository.insertCustomer(customer)
    }
}