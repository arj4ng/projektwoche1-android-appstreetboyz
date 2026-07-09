package com.example.projektwohce1_android_appstreetboyz.ui.screens.quiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projektwohce1_android_appstreetboyz.audioplayer.AudioPlayer
import com.example.projektwohce1_android_appstreetboyz.data.model.QuestionType
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuizViewModel
import java.util.concurrent.TimeUnit
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter

@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    audioPlayer: AudioPlayer? = null
) {
    val currentIndex by viewModel.currentIndex.collectAsState()
    val selectedAnswer by viewModel.selectedAnswer.collectAsState()
    val score by viewModel.score.collectAsState()
    val isFinished by viewModel.isFinished.collectAsState()

    if (isFinished) {
        ResultContent(
            score = score,
            total = viewModel.totalQuestions,
            audioPlayer = audioPlayer,
            onRestart = { viewModel.restart() }
        )
        return
    }

    val question = viewModel.currentQuestion()
    val options = if (question.type == QuestionType.TRUE_FALSE) {
        listOf("Wahr", "Falsch")
    } else {
        question.options
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 18.dp),
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
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "Quizmodus",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatusPill(
                        modifier = Modifier.weight(1f),
                        label = "Fortschritt",
                        value = "${currentIndex + 1}/${viewModel.totalQuestions}"
                    )
                    StatusPill(
                        modifier = Modifier.weight(1f),
                        label = "Punkte",
                        value = score.toString()
                    )
                }
            }
        }

        AnimatedContent(
            targetState = question.question,
            transitionSpec = {
                (slideInHorizontally { it } + fadeIn())
                    .togetherWith(slideOutHorizontally { -it } + fadeOut())
            },
            label = "QuestionAnimation"
        ) { questionText ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Frage",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = questionText,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        options.forEach { option ->
            AnswerButton(
                text = option,
                selectedAnswer = selectedAnswer,
                currentAnswer = question.correctAnswer,
                onClick = {
                    val isCorrect = viewModel.selectAnswer(option)
                    if (isCorrect) {
                        audioPlayer?.playCorrect()
                    } else {
                        audioPlayer?.playWrong()
                    }
                }
            )
        }

        if (selectedAnswer != null) {
            Button(
                onClick = {
                    audioPlayer?.playFlip()
                    viewModel.nextQuestion()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                val isLast = currentIndex == viewModel.totalQuestions - 1
                Text(if (isLast) "Ergebnis anzeigen" else "Weiter")
            }
        }

        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Composable
private fun StatusPill(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(label)
            Text(value)
        }
    }
}

@Composable
private fun AnswerButton(
    text: String,
    selectedAnswer: String?,
    currentAnswer: String,
    onClick: () -> Unit
) {
    val answered = selectedAnswer != null

    val containerColor = when {
        answered && text == currentAnswer -> Color(0xFF4CAF50)
        answered && text == selectedAnswer -> Color(0xFFF44336)
        else -> MaterialTheme.colorScheme.secondaryContainer
    }

    val contentColor = when {
        answered && (text == currentAnswer || text == selectedAnswer) -> Color.White
        else -> MaterialTheme.colorScheme.onSecondaryContainer
    }

    Button(
        onClick = onClick,
        enabled = !answered,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 6.dp)
        )
    }
}

@Composable
private fun ResultContent(
    score: Int,
    total: Int,
    audioPlayer: AudioPlayer?,
    onRestart: () -> Unit
) {
    LaunchedEffect(Unit) {
        when {
            score == total -> audioPlayer?.playPerfect()
            score < total / 2 -> audioPlayer?.playLose()
            else -> audioPlayer?.playVictory()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (score == total) {
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = listOf(
                    Party(
                        speed = 25f,
                        maxSpeed = 50f,
                        angle = 270,
                        spread = 360,
                        emitter = Emitter(
                            duration = 2,
                            TimeUnit.SECONDS
                        ).perSecond(200),
                        position = Position.Relative(0.5, 0.0)
                    )
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.18f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    Text(
                        text = "Quiz beendet!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = "Du hast $score von $total Fragen richtig beantwortet.",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Button(
                        onClick = onRestart,
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null
                        )
                        Text(" Nochmal spielen")
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun QuizScreenPreview() {
    QuizScreen(viewModel = QuizViewModel())
}
