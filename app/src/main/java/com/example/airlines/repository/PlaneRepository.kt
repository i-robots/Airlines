package com.example.airlines.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.airlines.data.PlaneDao
import com.example.airlines.data.Plane
import com.example.airlines.network.PlaneApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class PlaneRepository (private val planeDao: PlaneDao) {
    fun allPlanes(): LiveData<List<Plane>> = planeDao.getAllPlanes()

    fun getPlaneByPlaneNo(no:Int){
        planeDao.getPlaneByNo(no)
    }

    fun insertPlane(plane: Plane){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                PlaneApiService.getInstance().
                    insertPlaneAsync(plane).await()
            Log.d("", response.message())
        }
        planeDao.insertPlane(plane)
    }

    fun updatePlane(plane: Plane){
        planeDao.updatePlane(plane)
    }

    fun deletePlane(plane: Plane){
        planeDao.deletePlane(plane)
    }
}