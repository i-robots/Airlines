package com.example.airlines.viewmodel

import android.app.Application
import android.service.autofill.Transformation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.airlines.data.AppDatabase
import com.example.airlines.data.Book
import com.example.airlines.network.BookApiService
import com.example.airlines.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application){
    private val bookRepository: BookRepository
    val allBook: LiveData<List<Book>>

    init {
        val bookDao = AppDatabase.getDatabase(application).bookDao()
        bookRepository = BookRepository(bookDao, BookApiService.getInstance())
        allBook = bookRepository.allBooks()
    }

    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.insertBook(book)
    }

    fun getBookById(id:Int) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.getBookById(id)
    }

    fun updateBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.updateBook(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.deleteBook(book)
    }
}
