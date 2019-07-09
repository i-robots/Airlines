package com.example.airlines.viewmodel

import android.app.Application
import android.service.autofill.Transformation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.airlines.data.AppDatabase
import com.example.airlines.data.Flight
import com.example.airlines.network.FlightApiService
import com.example.airlines.repository.FlightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightViewModel(application: Application) : AndroidViewModel(application){
    private val flightRepository: FlightRepository
    val allFlight: LiveData<List<Flight>>

    init {
        val flightDao = AppDatabase.getDatabase(application).flightDao()
        flightRepository = FlightRepository(flightDao, FlightApiService.getInstance())
        allFlight = flightRepository.allFlights()
    }

    fun insertFlight(flight: Flight) = viewModelScope.launch(Dispatchers.IO){
        flightRepository.insertFlight(flight)
    }

    fun getFlightById(id:Int) = viewModelScope.launch(Dispatchers.IO){
        flightRepository.getFlightById(id)
    }

    fun updateFlight(flight: Flight) = viewModelScope.launch(Dispatchers.IO){
        flightRepository.updateFlight(flight)
    }

    fun deleteFlight(flight: Flight) = viewModelScope.launch(Dispatchers.IO){
        flightRepository.deleteFlight(flight)
    }
}
