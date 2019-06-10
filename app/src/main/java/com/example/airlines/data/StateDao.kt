package com.example.airlines.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete

@Dao
interface StateDao {
    @Query("SELECT * FROM state WHERE state_id = :id LIMIT 1")
    fun getStateById(id:Long):State

    @Query("SELECT * FROM state")
    fun getAllStates(): LiveData<List<State>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertState(state: State):Long

    @Update
    fun updateState(state: State):Int

    @Delete
    fun deleteState(state: State):Int
}