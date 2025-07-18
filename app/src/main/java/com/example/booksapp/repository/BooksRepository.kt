package com.example.booksapp.repository

import com.example.booksapp.data.DataOrException
import com.example.booksapp.model.Books
import com.example.booksapp.network.BooksApi
import javax.inject.Inject

open class BooksRepository @Inject constructor(private val api: BooksApi?) {

    open suspend fun getBooksData(quantity: String)
            : DataOrException<Books, Boolean, Exception> {
        val dataOrException = DataOrException<Books, Boolean, Exception>()
        dataOrException.loading = true
        try {
            val response = api?.getBooksData(query = quantity)
            dataOrException.data = response
            dataOrException.loading = false
        }catch (e: Exception){
            dataOrException.e = Exception("something went wrong")
            dataOrException.loading = false
        }
        return  dataOrException
    }

}