package com.example.projektwohce1_android_appstreetboyz.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontStyle
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuotesViewModel
import androidx.compose.foundation.layout.Row


@Composable
fun HomeScreen(
    flashcardsViewModel: FlashcardsViewModel,
    quotesViewModel: QuotesViewModel,
    onNavigateToFlashcards: () -> Unit
) {
    val quotes by quotesViewModel.quotes.collectAsState()
    val flashcards by  flashcardsViewModel.flashcards.collectAsState()
    val cardsToRepeat by flashcardsViewModel.cardsToRepeat.collectAsState()

    val dailyQuote = quotes.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Willkommen",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 24.dp)
        )
        if (dailyQuote != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "\"${dailyQuote.text}\"",
                        style = MaterialTheme.typography.bodyLarge,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "- ${dailyQuote.author}",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Dein Lernstatus:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "${flashcards.size} Karten insgesamt verfügbar"
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick =  { onNavigateToFlashcards() } ) {
            Text("Jetzt Lernen")
        }
        if (cardsToRepeat.isNotEmpty()){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Du hast ${cardsToRepeat.size} Karten zum Wiederholen!",
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = onNavigateToFlashcards
                    ) {
                        Text("Starten")
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        flashcardsViewModel = FlashcardsViewModel(),
        quotesViewModel = QuotesViewModel(),
        onNavigateToFlashcards = {}
    )
}