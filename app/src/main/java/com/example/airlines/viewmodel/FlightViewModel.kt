package com.example.airlines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.airlines.data.AppDatabase
import com.example.airlines.data.Flight
import com.example.airlines.repository.FlightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightViewModel(application: Application) : AndroidViewModel(application){
    private val flightRepository: FlightRepository
    val allFlight: LiveData<List<Flight>>

    init {
        val flightDao = AppDatabase.getDatabase(application).flightDao()
        flightRepository = FlightRepository(flightDao)
        allFlight = flightRepository.allFlights()
    }

    fun insertFlight(flight: Flight) = viewModelScope.launch(Dispatchers.IO){
        flightRepository.insertFlight(flight)
    }
}
