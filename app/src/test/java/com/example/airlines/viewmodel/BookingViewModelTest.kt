package com.example.airlines.viewmodel

import com.example.airlines.MainCoroutineRule
import com.example.airlines.data.Booking
import com.example.airlines.network.BookingApiService
import com.example.airlines.repository.BookingRepo.BookingHistoryRepository
import com.example.android.architecture.blueprints.todoapp.addedittask.FakeBookingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.ContinuationInterceptor
@ExperimentalCoroutinesApi
class BookingViewModelTest {
    private lateinit var bookingViewModel: BookingViewModel
    private val bookingApiService=BookingApiService.getInstance()
    private val bookingHistoryRepository= FakeBookingRepository(bookingApiService)
    val testContext= TestCoroutineScope()

        @get:Rule
        var mainCoroutineRule = MainCoroutineRule()
    @Before
    fun setupStatisticsViewModel() {
        bookingViewModel= BookingViewModel(bookingHistoryRepository)
    }
    @Test
    fun insertBooking() = mainCoroutineRule.runBlockingTest {

        val newBooking= Booking(1,"Addis-Ababa","BahirDar",2,"one-way")
        bookingViewModel.insertBooking(newBooking)
        assertEquals(bookingHistoryRepository.bookingsServiceData.containsValue(newBooking),true)
    }
    }


