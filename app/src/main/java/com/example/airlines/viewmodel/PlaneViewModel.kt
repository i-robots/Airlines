package com.example.airlines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.airlines.data.AppDatabase
import com.example.airlines.data.Plane
import com.example.airlines.repository.PlaneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaneViewModel(application: Application): AndroidViewModel(application) {
    private val planeRepository: PlaneRepository
    val allPlane: LiveData<List<Plane>>

    init {
        val planeDao = AppDatabase.getDatabase(application).planeDao()
        planeRepository = PlaneRepository(planeDao)
        allPlane = planeRepository.allPlanes()
    }

    fun insertPlane(plane: Plane) = viewModelScope.launch(Dispatchers.IO){
        planeRepository.insertPlane(plane)
    }

    fun getPlaneById(id:Int)= viewModelScope.launch(Dispatchers.IO){
        planeRepository.getPlaneByPlaneNo(id)
    }

    fun updatePlane(plane: Plane) = viewModelScope.launch(Dispatchers.IO){
        planeRepository.updatePlane(plane)
    }

    fun deletePlane(plane: Plane) = viewModelScope.launch(Dispatchers.IO){
        planeRepository.deletePlane(plane)
    }

}