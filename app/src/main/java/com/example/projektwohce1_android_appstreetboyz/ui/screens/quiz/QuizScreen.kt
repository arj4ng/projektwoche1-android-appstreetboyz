package com.example.projektwohce1_android_appstreetboyz.ui.screens.quiz


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projektwohce1_android_appstreetboyz.data.model.QuestionType
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuizViewModel


@Composable
fun QuizScreen(viewModel: QuizViewModel) {

    val currentIndex by viewModel.currentIndex.collectAsState()
    val selectedAnswer by viewModel.selectedAnswer.collectAsState()
    val score by viewModel.score.collectAsState()
    val isFinished by viewModel.isFinished.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isFinished) {
            ResultContent(
                score = score,
                total = viewModel.totalQuestions,
                onRestart = { viewModel.restart() }
            )
        } else {

            val question = viewModel.currentQuestion()

            Text(
                text = "Frage ${currentIndex + 1} von ${viewModel.totalQuestions}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            val options = if (question.type == QuestionType.TRUE_FALSE) {
                listOf("Wahr", "Falsch")
            } else {
                question.options
            }

            options.forEach { option ->
                AnswerButton(
                    text = option,
                    selectedAnswer = selectedAnswer,
                    currentAnswer = question.correctAnswer,
                    onClick = { viewModel.selectAnswer(option) }
                )
                Spacer(modifier = Modifier.height(12.dp))

            }

            if (selectedAnswer != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { viewModel.nextQuestion() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val isLast = currentIndex == viewModel.totalQuestions - 1
                    Text(if (isLast) "Ergebnis anzeigen" else "Weiter")
                }
            }
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

    Button(
        onClick = onClick,
        enabled = !answered,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = MaterialTheme.colorScheme.primary, disabledContainerColor = containerColor
        )

    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }


}
@Composable
private fun ResultContent(
    score: Int,
    total: Int,
    onRestart: () -> Unit

) {
    Text(
        text = "Quiz beendet!",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Du hast $score von $total Fragen richtig beantwortet.",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(32.dp))
    Button(onClick = onRestart) {
        Text("Nochmal Spielen")
    }


}
@Preview(showSystemUi = true)
@Composable
private fun QuizScreenPreview() {
    QuizScreen(viewModel = QuizViewModel())
}
