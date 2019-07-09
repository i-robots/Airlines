package com.example.airlines.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete

@Dao
interface FlightDao {
    @Query("SELECT * FROM flight WHERE flight_no = :no LIMIT 1")
    fun getFlightByNo(no:Int):LiveData<Flight>

    @Query("SELECT * FROM flight")
    fun getAllFlights():LiveData<List<Flight>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFlight(flight: Flight):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(flights: List<Flight>)

    @Update
    fun updateFlight(flight: Flight):Int

    @Delete
    fun deleteFlight(flight: Flight):Int
}