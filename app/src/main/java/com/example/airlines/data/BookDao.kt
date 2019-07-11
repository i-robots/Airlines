package com.example.airlines.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete

@Dao
interface BookDao {

    @Query("SELECT * FROM book WHERE ticketNo = :no LIMIT 1")
    fun getBookByNo(no:Int):LiveData<Book>

    @Query("SELECT * FROM book")
    fun getAllBooks():LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<Book>)

    @Update
    fun updateBook(book: Book):Int

    @Delete
    fun deleteBook(book: Book):Int
}