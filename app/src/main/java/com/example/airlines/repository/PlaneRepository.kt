package com.example.airlines.repository

import androidx.lifecycle.LiveData
import com.example.airlines.data.PlaneDao
import com.example.airlines.data.Plane

class PlaneRepository (private val planeDao: PlaneDao) {
    fun allPlanes(): LiveData<List<Plane>> = planeDao.getAllPlanes()

    fun insertPlane(plane: Plane){
        planeDao.insertPlane(plane)
    }
}