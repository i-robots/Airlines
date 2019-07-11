package com.example.airlines.network

import com.example.airlines.data.Book
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface BookApiService {
    @GET("books/{bookNo}")
    fun findByBookNoAsync(@Path("bookNo") bookNo: Int): Deferred<Response<Book>>

    @GET("books")
    fun getAllBooksAsync(): Deferred<Response<List<Book>>>

    @POST("books")
    fun insertBookAsync(@Body newBook: Book): Deferred<Response<Void>>

    @PUT("books/{id}")
    fun updateBookAsnc(@Path("id") id: Int, @Body newBook: Book): Deferred<Response<Void>>

    @DELETE("books/{id}")
    fun deleteBookAsync(@Path("id") id: Int): Deferred<Response<Void>>

    companion object {

        private val baseUrl = "http://192.168.43.174:5001/api/"

        fun getInstance(): BookApiService {

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

            return retrofit.create(BookApiService::class.java)
        }
    }
}