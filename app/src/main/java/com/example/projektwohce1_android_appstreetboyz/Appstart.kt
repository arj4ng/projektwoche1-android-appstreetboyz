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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.projektwohce1_android_appstreetboyz.audioplayer.AudioPlayer
import com.example.projektwohce1_android_appstreetboyz.ui.screens.flashcards.TopicScreen
import com.example.projektwohce1_android_appstreetboyz.ui.screens.quiz.QuizScreen
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuizViewModel


@Composable
fun Appstart(
    flashcardsViewModel: FlashcardsViewModel,
    quotesViewModel: QuotesViewModel,
    quizViewModel: QuizViewModel
){
    //AUDIOPLAYER
    val context = LocalContext.current
    val audioPlayer = remember {
        AudioPlayer(context)
    }
    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.release()
        }

    }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()//meldet jedes mal wenn die Seite wechselt
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationItems = listOf(
        Route.Home,
        Route.Flashcards,
        Route.Quotes,
        Route.Quiz
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
                            val finalRoute = if (screen.route == Route.Flashcards.route) {
                                Route.TopicSelection.route
                            } else {
                                screen.route
                            }
                            navController.navigate(finalRoute) {
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
                        flashcardsViewModel.setRepeatMode(false)
                        navController.navigate(Route.TopicSelection.route)
                    },
                    onStartRepeat = {
                        flashcardsViewModel.setRepeatMode(true)
                        navController.navigate(Route.Flashcards.route)
                    }
                )
            }
            composable(Route.TopicSelection.route) {
                TopicScreen(
                    viewModel = flashcardsViewModel,
                    onTopicSelected = {
                        navController.navigate(Route.Flashcards.route)
                    }
                )
            }
            composable(Route.Flashcards.route) {
                FlashcardScreen(
                    viewModel = flashcardsViewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    audioPlayer = audioPlayer
                )
            }
            composable(Route.Quotes.route) {
                QuotesScreen(
                    viewModel = quotesViewModel
                )
            }
            composable(Route.Quiz.route) {
                QuizScreen(
                    viewModel = quizViewModel,
                    audioPlayer = audioPlayer
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
        quotesViewModel = QuotesViewModel(),
        quizViewModel = QuizViewModel()
    )
}