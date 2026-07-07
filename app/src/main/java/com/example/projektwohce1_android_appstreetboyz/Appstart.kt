package com.example.projektwohce1_android_appstreetboyz

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projektwohce1_android_appstreetboyz.data.Route
import com.example.projektwohce1_android_appstreetboyz.ui.screens.flashcards.FlashcardScreen
import com.example.projektwohce1_android_appstreetboyz.ui.screens.home.HomeScreen
import com.example.projektwohce1_android_appstreetboyz.ui.screens.quotes.QuotesScreen
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuotesViewModel

@Composable
fun Appstart(
    flashcardsViewModel: FlashcardsViewModel,
    quotesViewModel: QuotesViewModel
){
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier
                .padding(innerPadding)
        ){
            composable(Route.Home.route) {
                HomeScreen(
                    flashcardsViewModel = flashcardsViewModel,
                    quotesViewModel = quotesViewModel,
                    onNavigateToFlashcards = {
                        navController.navigate(Route.Flashcards.route)
                    }
                )
            }
            composable(Route.Flashcards.route) {
                FlashcardScreen(
                    viewModel = flashcardsViewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
            composable(Route.Quotes.route) {
                QuotesScreen(
                    viewModel = quotesViewModel
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AppstartPreview() {
    Appstart(
        flashcardsViewModel = FlashcardsViewModel(),
        quotesViewModel = QuotesViewModel()
    )
}