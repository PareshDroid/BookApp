package com.example.booksapp.screens.details

import androidx.lifecycle.ViewModel
import com.example.booksapp.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(): ViewModel() {
    private val _selectedBook = MutableStateFlow<Data?>(null)
    val selectedBook: StateFlow<Data?> = _selectedBook

    fun selectBook(book: Data) {
        _selectedBook.value = book
    }
}