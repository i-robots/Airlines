package com.example.airlines.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.airlines.data.Book
import com.example.airlines.data.BookDao
import com.example.airlines.network.BookApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class BookRepository(private val bookDao: BookDao, private val bookApiService: BookApiService) {
    fun allBooks(): LiveData<List<Book>> {
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<List<Book>> = bookApiService.getAllBooksAsync().await()
            for(book in response.body()!!){
                bookDao.insertBook(book)
            }
        }
        return bookDao.getAllBooks()
    }

    fun getBookById(id:Int): LiveData<Book> {
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Book> = bookApiService.findByBookNoAsync(id).await()
            bookDao.insertBook(response.body()!!)
        }
        return bookDao.getBookByNo(id)
    }

    fun insertBook(book: Book){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                bookApiService.insertBookAsync(book).await()
            Log.d("", "${response.body()}")
        }
        bookDao.insertBook(book)
    }

    fun updateBook(book: Book){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                bookApiService.updateBookAsnc(book.ticketNo,book).await()
            Log.d("", "${response.body()}")
        }
        bookDao.updateBook(book)
    }

    fun deleteBook(book: Book){
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<Void> =
                bookApiService.deleteBookAsync(book.ticketNo).await()
            Log.d("", "${response.body()}")
        }
        bookDao.deleteBook(book)
    }
}