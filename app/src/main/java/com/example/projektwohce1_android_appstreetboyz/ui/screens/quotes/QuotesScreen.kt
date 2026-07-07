package com.example.projektwohce1_android_appstreetboyz.ui.screens.quotes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projektwohce1_android_appstreetboyz.data.model.QuoteCategory
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuotesViewModel
import androidx.compose.foundation.layout.FlowRow
import com.example.projektwohce1_android_appstreetboyz.data.model.Quote

@Composable
fun QuotesScreen(
    viewModel: QuotesViewModel
) {
    val quotes by viewModel.quotes.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val favoriteQuoteIds by viewModel.favoriteQuoteIds.collectAsState()
    val showFavoritesOnly by viewModel.showFavoritesOnly.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "QuoteCraft",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (showFavoritesOnly) "Lieblingszitate" else "Zitate nach Kategorie",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            TextButton(onClick = { viewModel.toggleFavoritesOnly() }) {
                Text(if (showFavoritesOnly) "♥ Favoriten" else "♡ Favoriten")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { viewModel.selectCategory(null) },
                label = { Text("Alle") }
            )

            viewModel.categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { viewModel.selectCategory(category) },
                    label = { Text(category.toDisplayName()) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(quotes, key = { it.id }) { quote ->
                QuoteCard(
                    quote = quote,
                    isFavorite = quote.id in favoriteQuoteIds,
                    onFavoriteClick = { viewModel.toggleFavorite(quote.id) }
                )
            }
        }
    }
}

@Composable
private fun QuoteCard(
    quote: Quote,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = quote.category.toDisplayName(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Box(
                    modifier = Modifier
                        .clickable(onClick = onFavoriteClick)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = if (isFavorite) "♥" else "♡",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isFavorite) Color(0xFFD32F2F) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = "\"${quote.text}\"",
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

private fun QuoteCategory.toDisplayName(): String {
    return when (this) {
        QuoteCategory.PROGRAMMING_JOKE -> "Programming Joke"
        QuoteCategory.MOTIVATIONAL -> "Motivational"
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QuotesScreenPreview() {
    QuotesScreen(
        viewModel = QuotesViewModel()
    )
}
