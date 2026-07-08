package com.example.projektwohce1_android_appstreetboyz.ui.screens.flashcards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel
import com.example.projektwohce1_android_appstreetboyz.ViewModels.DataSource
import androidx.compose.runtime.getValue

@Composable
fun TopicScreen(
    viewModel: FlashcardsViewModel,
    onTopicSelected: () -> Unit
) {
    val categories = DataSource.categories
    val cardsToRepeat by viewModel.cardsToRepeat.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Wähle ein Thema, um zu üben.",
            style = MaterialTheme.typography.titleSmall
        )

        if(cardsToRepeat.isNotEmpty()) {
            Button(
                onClick = {
                    viewModel.setRepeatMode(true)
                    onTopicSelected()
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Text("🔄 Karten wiederholen (${cardsToRepeat.size})")
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        categories.forEach { category ->
            Button(
                onClick = {
                    viewModel.setRepeatMode(false)
                    viewModel.selectCategory(category)
                    onTopicSelected() // Navigiert zum FlashcardScreen
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(category)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TopicScreenPreview() {
    TopicScreen(
        viewModel = FlashcardsViewModel(),
        onTopicSelected = {}
    )
}