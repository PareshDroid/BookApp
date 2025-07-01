package com.example.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.screens.SplashScreen
import com.example.booksapp.screens.details.DetailScreen
import com.example.booksapp.screens.main.MainScreen

@ExperimentalComposeUiApi
@Composable
fun BooksNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BooksScreen.SplashScreen.name
    ) {
        composable(BooksScreen.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(BooksScreen.MainScreen.name) {
            MainScreen(navController = navController)
        }

        composable(BooksScreen.DetailScreen.name) {
            DetailScreen(navController = navController)
        }
    }
}
