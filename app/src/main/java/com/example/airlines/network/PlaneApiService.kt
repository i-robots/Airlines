package com.example.airlines.network

import com.example.airlines.data.Plane
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface PlaneApiService {
    @GET("planes/{id}")
    fun findByPlaneByNoAsync(@Path("planeNo") planeNo: Int): Deferred<Response<Plane>>

    @GET("planes")
    fun getAllPlanesAsync(@Query("dest") dest: String): Deferred<Response<Plane>>

    @POST("planes")
    fun insertPlaneAsync(@Body newPlane: Plane): Deferred<Response<Void>>

    @PUT("planes/{id}")
    fun updatePlaneAsnc(@Path("id") id: Long, @Body newPlane: Plane): Deferred<Response<Void>>

    @DELETE("planes/{id}")
    fun deletePlaneAsync(@Path("id") id: Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://127.0.0.1:9090/api/"

        fun getInstance(): PlaneApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(PlaneApiService::class.java)
        }
    }
}