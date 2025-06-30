package com.example.booksapp.model

data class Books(
    val code: Int,
    val `data`: List<Data>,
    val locale: String,
    val seed: Any,
    val status: String,
    val total: Int
)