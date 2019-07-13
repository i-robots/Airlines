package com.example.airlines.repository.Fakes

import com.example.airlines.data.Booking
import com.example.airlines.data.Customer
import com.example.airlines.data.CustomerDao
import com.example.airlines.repository.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.LinkedHashMap

class FakeCustomerRepository(private val customerDao: CustomerDao):CustomerRepository(customerDao){
    var customersServiceData: LinkedHashMap<Int, Customer> = LinkedHashMap()

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }


    override suspend fun insertCustomer(customer:Customer){
        withContext(Dispatchers.IO){
            customerDao.insertCustomer(customer)}
}
    override suspend fun getAllCustomers(){
        withContext(Dispatchers.IO){
            customerDao.getAllCustomers()
    }
}}