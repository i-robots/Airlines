package com.example.airlines.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.airlines.MainCoroutineRule
import com.example.airlines.data.AppDatabase
import com.example.airlines.data.Customer
import com.example.airlines.data.CustomerDao
import com.example.airlines.repository.Fakes.FakeCustomerRepository
import com.example.airlines.viewmodel.CustomerViewModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class CustomerRepositoryTest(customerDao:CustomerDao) {

    private lateinit var customerViewModel: CustomerViewModel
//        private val customerApiService=CustomerApiService.getInstance()

    private lateinit var database:AppDatabase
    private lateinit var customerDao:CustomerDao
    private lateinit var customerRepository: FakeCustomerRepository
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    @Before
    fun setupStatisticsViewModel() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        customerDao=database.customerDao()
        customerRepository= FakeCustomerRepository(customerDao)
    }
    @Test
    fun insertCustomer() = mainCoroutineRule.runBlockingTest {

        val newCustomer= Customer(1,"name","female","Ethio","1/2/1998")
        customerViewModel.insertCustomer(newCustomer)
//            val c=customerViewModel.customerRepository.getCustomerById(newCustomer.id)
        assertEquals(customerRepository.customersServiceData.containsValue(newCustomer),false)
    }

}