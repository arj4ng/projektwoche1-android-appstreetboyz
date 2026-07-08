package com.example.projektwohce1_android_appstreetboyz.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Route(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : Route(
        route = "home",
        label = "Home",
        icon = Icons.Default.Home
    )
    object Flashcards : Route(
        route = "flashcards",
        label = "Lernen",
        icon = Icons.Default.School
    )
    object Quiz : Route(
        route = "quiz",
        label = "Quiz",
        icon = Icons.Default.Quiz
    )
    object Quotes : Route (
        route = "quotes",
        label = "Quotes",
        icon = Icons.Default.FormatQuote
    )
}
