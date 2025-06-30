package com.example.booksapp.network

import com.example.booksapp.model.Books
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BooksApi {
    @GET(value = "v2/books")
    suspend fun getBooksData(
        @Query("_quantity") query : String
                          ): Books
}