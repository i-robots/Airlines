/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.example.airlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.airlines.data.Book
import com.example.airlines.data.Flight
import com.example.airlines.databinding.BookFragmentBinding
import com.example.airlines.viewmodel.BookViewModel
import com.example.airlines.viewmodel.FlightViewModel
import java.lang.Math.random


class bookFragment : Fragment() {

    private lateinit var binding: BookFragmentBinding
    private lateinit var bookViewModel: BookViewModel
    private lateinit var flightViewModel: FlightViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.book_fragment, container, false)

        val countryAdapter = ArrayAdapter.createFromResource(binding.root.context,R.array.country,
            android.R.layout.simple_spinner_item)

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDestCountry.adapter = countryAdapter
        binding.spinnerSourceCountry.adapter = countryAdapter

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        flightViewModel = ViewModelProviders.of(this).get(FlightViewModel::class.java)

        val randNo = random()*1000

        binding.bookFlightButton.setOnClickListener{
            flightViewModel.getFlightByRootAndDest(binding.spinnerSourceCountry.selectedItem.toString(),
                binding.spinnerDestCountry.selectedItem.toString()).observe(this, Observer {
                    flight->flight?.let {
                val book = readFields(
                    randNo.toInt(),
                    flight,
                    binding.radioGroup.checkedRadioButtonId,
                    binding.numberofpassengers.text.toString().toInt())
                    bookViewModel.insertBook(book)
            }
            })
        }

        return binding.root
    }

    private fun readFields(ticketNo:Int, flight: Flight, noOfWays:Int, noOfPass:Int): Book {
        return Book(ticketNo,flight,noOfWays,noOfPass)
    }
}
