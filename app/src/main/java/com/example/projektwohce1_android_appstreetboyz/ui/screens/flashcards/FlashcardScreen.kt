package com.example.projektwohce1_android_appstreetboyz.ui.screens.flashcards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel

@Composable
fun FlashcardScreen(
    viewModel: FlashcardsViewModel,
    onNavigateBack: () -> Unit
) {
    val flashcards by viewModel.filteredFlashcards.collectAsState()
    val currentIndex by viewModel.currentIndex.collectAsState()

    var isFlipped by remember { mutableStateOf(false) }
    val currentCard = flashcards[currentIndex]
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val cardColor = when {
        offsetX.value > 150f -> Color(0xFFC8E6C9) // Ein sanftes Grün (Rechts = Gelernt)
        offsetX.value < -150f -> Color(0xFFFFCDD2) // Ein sanftes Rot (Links = Wiederholen)
        isFlipped -> MaterialTheme.colorScheme.tertiaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            // Die Karte folgt dem Finger seitlich
                            scope.launch { offsetX.snapTo(offsetX.value + dragAmount.x) }
                        },
                        onDragEnd = {
                            // Wenn weit genug gewischt wurde (z.B. 400 Pixel)
                            if (offsetX.value > 400) {
                                // Nach RECHTS (Gelernt)
                                viewModel.swipeRight(currentCard)
                                scope.launch { offsetX.snapTo(0f) } // Zurück in die Mitte für die neue Karte
                            } else if (offsetX.value < -400) {
                                // Nach LINKS (Wiederholen)
                                viewModel.swipeLeft(currentCard)
                                scope.launch { offsetX.snapTo(0f) }
                            } else {
                                // Nicht weit genug gewischt -> Karte springt in die Mitte zurück
                                scope.launch {
                                    offsetX.animateTo(
                                        0f,
                                        spring()
                                    )
                                }
                            }
                            isFlipped = false // Neue Karte immer mit der Frage zeigen
                        }
                    )
                }
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable { isFlipped = !isFlipped },
            colors = CardDefaults.cardColors(
                containerColor = cardColor
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        if (rotation > 90f) rotationY = 180f
                    }

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isFlipped) "ANTWORT" else "FRAGE",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                    Text(
                        text = if (isFlipped) currentCard.answer else currentCard.question,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Text(
            text = "Karte ${currentIndex + 1} von ${flashcards.size}",
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { viewModel.previousCard() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "zurück",
                )
            }

        }
        TextButton(onClick = {
            viewModel.selectCategory(null)
            onNavigateBack()
        }) {
            Text("Thema wechseln")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FlashcardScreenPreview() {
    FlashcardScreen(
        viewModel = FlashcardsViewModel(),
        onNavigateBack = {}
    )
}