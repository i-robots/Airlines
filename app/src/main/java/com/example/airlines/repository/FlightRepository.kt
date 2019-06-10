package com.example.airlines.repository

import androidx.lifecycle.LiveData
import com.example.airlines.data.Flight
import com.example.airlines.data.FlightDao

class FlightRepository(private val flightDao: FlightDao) {
    fun allFlights(): LiveData<List<Flight>> = flightDao.getAllFlights()

    fun insertFlight(flight: Flight){
        flightDao.insertFlight(flight)
    }
}