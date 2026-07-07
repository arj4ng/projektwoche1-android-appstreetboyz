package com.example.projektwohce1_android_appstreetboyz

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projektwohce1_android_appstreetboyz.data.Route
import com.example.projektwohce1_android_appstreetboyz.ui.screens.flashcards.FlashcardScreen
import com.example.projektwohce1_android_appstreetboyz.ui.screens.home.HomeScreen
import com.example.projektwohce1_android_appstreetboyz.ui.screens.quotes.QuotesScreen
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuotesViewModel
import androidx.compose.runtime.getValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

@Composable
fun Appstart(
    flashcardsViewModel: FlashcardsViewModel,
    quotesViewModel: QuotesViewModel
){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()//meldet jedes mal wenn die Seite wechselt
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationItems = listOf(
        Route.Home,
        Route.Flashcards,
        Route.Quotes
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar =  {
            NavigationBar {
                navigationItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(
                            imageVector =screen.icon,
                            contentDescription = screen.label
                        )},
                        label = { Text(screen.label)},
                        selected = currentRoute == screen.route,//logik für die Buttonfarbe (gedrückt/nichtgedrückt)
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true //verhindert das sich der "Zurück Stapel" unendlich füllt
                                }
                                launchSingleTop = true //Verhindert das die gleich Seite mehrmals oben liegt
                                restoreState = true //merk sich den Scrollzustand der Seite
                            }
                        }
                    )
                }
            }
        }
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