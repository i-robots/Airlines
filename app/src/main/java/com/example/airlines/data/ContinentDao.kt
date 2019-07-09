package com.example.airlines.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ContinentDao {
    @Query("SELECT * FROM continent")
    fun getAllContinents(): LiveData<List<Continent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(continents: List<Continent>)
}
