package com.example.booksapp.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booksapp.model.Books
import com.example.booksapp.model.Data
import com.example.booksapp.screens.MainViewModel

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {

    MainScreenContent(mainViewModel)
}

@Composable
fun MainScreenContent(mainViewModel: MainViewModel) {
    val dataOrException by mainViewModel.booksState.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.fetchBooks("10")
    }

    when {
        dataOrException.loading == true -> {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        }
        dataOrException.e != null -> {
            MainScreenErrorContentStateless(
                errorMessage = dataOrException.e?.localizedMessage ?: "Unknown error"
            )
        }
        dataOrException.data != null -> {
            MainScreenContentStateless(dataOrException.data!!)
        }
    }

}

@Composable
fun MainScreenErrorContentStateless(errorMessage: String) {
    Text(
        text = "Error: $errorMessage",
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun MainScreenContentStateless(books: Books) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(books.data) { book ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(book.title, style = MaterialTheme.typography.titleMedium)
                    Text(book.author, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}




@Preview(showBackground = true, apiLevel = 34, device = "id:pixel_9",
    name = "Pixel 9 Preview")
@Composable
fun ErrorPreview() {
    MainScreenErrorContentStateless("This is a sample error message for preview.")
}

@Preview(showBackground = true, apiLevel = 34, device = "id:pixel_9",
    name = "Pixel 9 Preview")
@Composable
fun DefaultPreview() {
    val sampleBooks = Books(
        code = 200,
        data = listOf(
            Data(
                author = "Sample Author",
                description = "A sample book for preview.",
                genre = "Fiction",
                id = 1,
                image = "https://example.com/sample.jpg",
                isbn = "1234567890",
                published = "2024",
                publisher = "Sample Publisher",
                title = "Sample Book"
            ),
            Data(
                author = "Sample Author",
                description = "A sample book for preview.",
                genre = "Fiction",
                id = 1,
                image = "https://example.com/sample.jpg",
                isbn = "1234567890",
                published = "2024",
                publisher = "Sample Publisher",
                title = "Sample Book"
            )
        ),
        locale = "en",
        seed = "",
        status = "success",
        total = 1
    )
    MainScreenContentStateless(books = sampleBooks)
}