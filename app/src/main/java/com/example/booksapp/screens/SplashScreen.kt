package com.example.booksapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booksapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate("MainScreen") {
            popUpTo("SplashScreen") { inclusive = true }
        }
    }
    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {

         Column(
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally,
         modifier = Modifier.padding(16.dp)
     ) {
         Image(
             painter = painterResource(id = R.drawable.ic_launcher_background),
             contentDescription = "Weather Icon",
             modifier = Modifier.size(100.dp),
             contentScale = ContentScale.Crop
         )
         Text(
             text = "Weather App",
             style = MaterialTheme.typography.headlineLarge,
             color = MaterialTheme.colorScheme.primary,
             modifier = Modifier.padding(top = 8.dp)
         )
     }


}

@Preview(showBackground = true, apiLevel = 34, device = "id:pixel_9",
    name = "Pixel 9 Preview")
@Composable
fun DefaultPreview() {

    SplashScreenContent()
}