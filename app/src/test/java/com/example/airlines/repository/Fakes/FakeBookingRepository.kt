/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.architecture.blueprints.todoapp.addedittask

import androidx.annotation.VisibleForTesting
import com.example.airlines.data.Booking
import com.example.airlines.network.BookingApiService
import com.example.airlines.repository.BookingRepo.BookingHistoryRepository
import com.google.common.collect.Lists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.tools.ant.Task
import retrofit2.Response
import java.util.LinkedHashMap

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
class FakeBookingRepository(private val bookingApiService: BookingApiService) : BookingHistoryRepository(bookingApiService) {

    var bookingsServiceData: LinkedHashMap<Int, Booking> = LinkedHashMap()

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }


    override suspend fun insertBooking(newBooking: Booking):Response<Void> =
       withContext(Dispatchers.IO){
      bookingApiService.insertBooking(newBooking).await()}
}
