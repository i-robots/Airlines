package com.example.airlines.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.airlines.data.Flight
import com.example.airlines.data.FlightDao
import com.example.airlines.network.FlightApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class FlightRepository(private val flightDao: FlightDao, private val flightApiService: FlightApiService) {
    fun allFlights(): LiveData<List<Flight>> {
        GlobalScope.launch(Dispatchers.IO) {
           val response: Response<List<Flight>> = flightApiService.getAllFlightsAsync().await()
            for(flight in response.body()!!){
                flightDao.insertFlight(flight)
            }
        }
        return flightDao.getAllFlights()
    }

    fun getFlightById(id:Int):LiveData<Flight>{
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Flight> = flightApiService.findByFlightNoAsync(id).await()
            flightDao.insertFlight(response.body()!!)
        }
        return flightDao.getFlightByNo(id)
    }

    fun getFlightByDestAndRoot(root:String,dest:String):LiveData<Flight>{
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Flight> = flightApiService.findByFlightByRootAsync(root).await()
            flightDao.insertFlight(response.body()!!)
        }
        return flightDao.getFlightByRootAndDest(root,dest)
    }

    fun insertFlight(flight: Flight){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                flightApiService.insertFlightAsync(flight).await()
            Log.d("", "${response.body()}")
        }
        flightDao.insertFlight(flight)
    }

    fun updateFlight(flight: Flight){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                flightApiService.updateFlightAsnc(flight.flightNo,flight).await()
            Log.d("", "${response.body()}")
        }
        flightDao.updateFlight(flight)
    }

    fun deleteFlight(flight: Flight){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                flightApiService.deleteFlightAsync(flight.flightNo).await()
            Log.d("", "${response.body()}")
        }
        flightDao.deleteFlight(flight)
    }
}