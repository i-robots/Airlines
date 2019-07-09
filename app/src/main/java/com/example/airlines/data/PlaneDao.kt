package com.example.airlines.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete

@Dao
interface PlaneDao {
    @Query("SELECT * FROM plane WHERE plane_no = :no LIMIT 1")
    fun getPlaneByNo(no:Int):Plane

    @Query("SELECT * FROM plane")
    fun getAllPlanes():LiveData<List<Plane>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlane(plane: Plane):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(planes: List<Plane>)

    @Update
    fun updatePlane(plane: Plane):Int

    @Delete
    fun deletePlane(plane: Plane):Int
}