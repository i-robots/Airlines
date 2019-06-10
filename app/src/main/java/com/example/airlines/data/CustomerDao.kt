package com.example.airlines.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer WHERE customer_id = :id LIMIT 1")
    fun getCustomerById(id:Long):Customer

    @Query("SELECT * FROM customer")
    fun getAllCustomers():LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customer: Customer):Long

    @Update
    fun updateCustomer(customer: Customer):Int

    @Delete
    fun deleteCustomer(customer: Customer):Int
}