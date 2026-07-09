package com.example.projektwohce1_android_appstreetboyz.ui.screens.flashcards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import com.example.projektwohce1_android_appstreetboyz.audioplayer.AudioPlayer
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.draw.clip

@Composable
fun FlashcardScreen(
    viewModel: FlashcardsViewModel,
    onNavigateBack: () -> Unit,
    audioPlayer: AudioPlayer? = null
) {
    val cardsToRepeat by viewModel.cardsToRepeat.collectAsState()
    val flashcards by viewModel.filteredFlashcards.collectAsState()
    val currentIndex by viewModel.currentIndex.collectAsState()
    val isRepeatMode by viewModel.isRepeatMode.collectAsState()

    if (flashcards.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Super gemacht",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = when {
                    isRepeatMode -> "Alle Fehler korrigiert!"
                    cardsToRepeat.isNotEmpty() -> "Thema beendet! Du hast noch ${cardsToRepeat.size} Fehler offen."
                    else -> "Dieses Thema hast du durch."
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            if (!isRepeatMode && cardsToRepeat.isNotEmpty()) {
                Button(
                    onClick = { viewModel.setRepeatMode(true) }
                ) {
                    Text("Jetzt wiederholen")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    viewModel.setRepeatMode(false)
                    onNavigateBack()
                }
            ) {
                Text("Zurück zum Start")
            }
        }
        return
    }

    var isFlipped by remember { mutableStateOf(false) }
    
    // Sicherer Zugriff auf die Karte mit getOrNull
    val currentCard = flashcards.getOrNull(currentIndex)
    
    // Falls der Index kurzzeitig out of bounds ist (während Recomposition)
    if (currentCard == null) {
        // Wir könnten hier einen Ladeindikator zeigen oder einfach kurz warten
        return
    }

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
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = if (isRepeatMode) "Wiederholung aktiv" else "Lernmodus aktiv",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = if (!isRepeatMode && cardsToRepeat.isNotEmpty()) {
                        "Wische links für später, rechts für gelernt. Tippen = Karte drehen."
                    } else {
                        "Tippe zum Umdrehen. Wische klar nach links oder rechts für den nächsten Schritt."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.82f)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(340.dp)
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .pointerInput(currentCard.id) {
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
                                scope.launch { offsetX.snapTo(0f) }
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
                .clickable {
                    isFlipped = !isFlipped
                    audioPlayer?.playFlip()
                },
            colors = CardDefaults.cardColors(
                containerColor = cardColor
            ),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
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
                    )
                    Text(
                        text = if (isFlipped) currentCard.answer else currentCard.question,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Fortschritt",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.78f)
                    )
                    Text(
                        text = "${currentIndex + 1} / ${flashcards.size}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            OutlinedButton(
                onClick = { viewModel.previousCard() },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Zurück"
                )
                Text(" Zurück")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(
                onClick = {
                    viewModel.selectCategory(null)
                    onNavigateBack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Thema wechseln")
            }

            Button(
                onClick = {
                    viewModel.setRepeatMode(false)
                    onNavigateBack()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
                Text(" Übersicht")
            }
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
