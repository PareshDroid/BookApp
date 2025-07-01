package com.example.booksapp.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.DataOrException
import com.example.booksapp.model.Books
import com.example.booksapp.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(private val repository: BooksRepository)
    : ViewModel(){

    /**
     * Create similar to below example
     * private val _name = MutableStateFlow("John")
     *     val name: StateFlow<String> = _name.asStateFlow()
     *
     *     fun updateName(newName: String) {
     *         _name.value = newName
     *     }
     */

    private val _booksState = MutableStateFlow(DataOrException<Books, Boolean, Exception>())
    //asStateFlow() is an extension function to expose a read-only version of a MutableStateFlow.
    open val booksState: StateFlow<DataOrException<Books, Boolean, Exception>> = _booksState.asStateFlow()

    init {
        fetchBooks("10")
    }

    fun fetchBooks(quantity: String) {
        viewModelScope.launch {
            _booksState.value = DataOrException(loading = true)
            _booksState.value = repository.getBooksData(quantity)
        }
    }
}