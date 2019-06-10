package com.example.airlines.network

import com.example.airlines.data.Customer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface CustomerApiService{
    @GET("customer/{id}")
    fun findByCustomerIdAsync(@Path("id") id: Long): Deferred<Response<Customer>>

    @GET("customer")
    fun getAllCustomersAsync(): Deferred<Response<List<Customer>>>

    @POST("customer")
    fun insertCustomerAsync(@Body newCustomer: Customer): Deferred<Response<Void>>

    @PUT("customer/{id}")
    fun updateCustomerAsnc(@Path("id") id: Long, @Body newCustomer: Customer): Deferred<Response<Void>>

    @DELETE("customer/{id}")
    fun deleteCustomerAsync(@Path("id") id: Long): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://127.0.0.1:9090/api/"

        fun getInstance(): CustomerApiService {

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

            return retrofit.create(CustomerApiService::class.java)
        }
    }
}