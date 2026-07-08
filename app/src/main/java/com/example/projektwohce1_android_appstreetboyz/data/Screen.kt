package com.example.projektwohce1_android_appstreetboyz.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
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
        icon = Icons.Default.Star
    )
    object Quiz : Route(
        route = "quiz",
        label = "Quiz",
        icon = Icons.Default.Star
    )
    object Quotes : Route (
        route = "quotes",
        label = "Quotes",
        icon = Icons.Default.Star
    )
    object TopicSelection: Route(
        route = "topic_selection",
        label = "Themen",
        icon = Icons.Default.List
    )
}