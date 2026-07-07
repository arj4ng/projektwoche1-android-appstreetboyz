package com.example.projektwohce1_android_appstreetboyz.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Flashcards : Screen(
        route = "flashcards",
        label = "Lernen",
        icon = Icons.Default.Star
    )
    object Quiz : Screen(
        route = "quiz",
        label = "Quiz",
        icon = Icons.Default.Star
    )
    object Quotes : Screen (
        route = "quotes",
        label = "Quotes",
        icon = Icons.Default.Star
    )
}