package com.example.airlines.repository

import androidx.lifecycle.LiveData
import com.example.airlines.data.Customer
import com.example.airlines.data.CustomerDao

class CustomerRepository(private val customerDao: CustomerDao) {
    fun allCustomers(): LiveData<List<Customer>> = customerDao.getAllCustomers()

    fun insertCustomer(customer: Customer){
        customerDao.insertCustomer(customer)
    }
}