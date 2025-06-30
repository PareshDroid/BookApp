package com.example.booksapp.screens

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
class MainViewModel @Inject constructor(private val repository: BooksRepository)
    : ViewModel(){

    private val _booksState = MutableStateFlow(DataOrException<Books, Boolean, Exception>())
    val booksState: StateFlow<DataOrException<Books, Boolean, Exception>> = _booksState.asStateFlow()

    fun fetchBooks(quantity: String) {
        viewModelScope.launch {
            _booksState.value = DataOrException(loading = true)
            _booksState.value = repository.getBooksData(quantity)
        }
    }
}