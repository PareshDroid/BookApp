package com.example.booksapp.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booksapp.model.Books
import com.example.booksapp.model.Data
import com.example.booksapp.screens.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedTab by remember { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Navigation",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { /* Handle navigation */ }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Profile") },
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { /* Handle navigation */ }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Books App") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 }
                    )
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                MainScreenContent(mainViewModel)
            }
        }
    }
}



@Composable
fun MainScreenContent(mainViewModel: MainViewModel) {
    //collectAsState - Jetpack Compose API to observe StateFlow in Composables
    val dataOrException by mainViewModel.booksState.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.fetchBooks("10")
    }

    when {
        dataOrException.loading == true -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(modifier = Modifier.padding(32.dp))
            }
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