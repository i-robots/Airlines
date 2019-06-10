package com.example.airlines.network

import com.example.airlines.data.Flight
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface FlightApiService {
    @GET("flights/{id}")
    fun findByFlightNoAsync(@Path("flightNo") flightNo: Int): Deferred<Response<Flight>>

    @GET("flights")
    fun findByDestinationAsync(@Query("dest") dest: String): Deferred<Response<Flight>>

    @GET("flights")
    fun getAllFlightsAsync(): Deferred<Response<List<Flight>>>

    @POST("flights")
    fun insertFlightAsync(@Body newFlight: Flight): Deferred<Response<Void>>

    @PUT("flights/{id}")
    fun updateFlightAsnc(@Path("id") id: Long, @Body newFlight: Flight): Deferred<Response<Void>>

    @DELETE("flights/{id}")
    fun deleteFlightAsync(@Path("id") id: Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://127.0.0.1:9090/api/"

        fun getInstance(): FlightApiService {

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

            return retrofit.create(FlightApiService::class.java)
        }
    }
}