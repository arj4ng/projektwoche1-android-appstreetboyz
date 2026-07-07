package com.example.projektwohce1_android_appstreetboyz.data.model

//QuizQuestion (Gemischt MultiChoice oder wahr/falsch)
data class QuizQuestion(
    val question: String,
    val options: List<String> = emptyList(), // Leer für wahr/falsch
    val correctAnswer: String,
    val type: QuestionType
)
enum class QuestionType {
    MULTIPLE_CHOICE, TRUE_FALSE
}
