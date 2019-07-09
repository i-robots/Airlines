package com.example.airlines.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.airlines.data.Customer
import com.example.airlines.data.CustomerDao
import com.example.airlines.network.CustomerApiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class CustomerRepository(private val customerDao: CustomerDao) {
    fun allCustomers(): LiveData<List<Customer>>{
        return customerDao.getAllCustomers()
    }

    fun getCustomerById(id:Long): Customer? {
        val customer = customerDao.getCustomerById(id)
        if( customer == null){
            lateinit var response: Deferred<Response<Customer>>
            GlobalScope.launch(Dispatchers.IO) {
                 response = CustomerApiService.getInstance().findByCustomerIdAsync(id)
            }
            return response.getCompleted().body()
        }else{
            return customer
        }
    }

    fun insertCustomer(customer: Customer){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                CustomerApiService.getInstance().
                    insertCustomerAsync(customer).await()
            Log.d("", response.message())
        }
        customerDao.insertCustomer(customer)
    }

    fun updateCustomer(customer: Customer){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                CustomerApiService.getInstance().
                    updateCustomerAsync(customer.id,customer).await()
            Log.d("", response.message())
        }
        customerDao.updateCustomer(customer)
    }

    fun deleteCustomer(customer: Customer){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                CustomerApiService.getInstance().
                    deleteCustomerAsync(customer.id).await()
            Log.d("", response.message())
        }
        customerDao.deleteCustomer(customer)
    }
}