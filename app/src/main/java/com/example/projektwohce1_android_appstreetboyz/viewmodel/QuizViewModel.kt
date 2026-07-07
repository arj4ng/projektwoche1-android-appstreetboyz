package com.example.projektwohce1_android_appstreetboyz.viewmodel


import androidx.lifecycle.ViewModel
import com.example.projektwohce1_android_appstreetboyz.ViewModels.DataSource
import com.example.projektwohce1_android_appstreetboyz.data.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class QuizViewModel: ViewModel() {

    private val question: List<QuizQuestion> = DataSource.quizQuestions

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex

    private val _selectedAnswer = MutableStateFlow<String?>(null)
    val selectedAnswer: StateFlow<String?> = _selectedAnswer

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private val _isFinished = MutableStateFlow(false)
    val isFinished: StateFlow<Boolean> = _isFinished

    val totalQuestions: Int = question.size


    fun currentQuestion(): QuizQuestion = question[_currentIndex.value.coerceIn(0, question.lastIndex)]

    fun selectAnswer(answer: String) {
        if (_selectedAnswer.value != null) return

        _selectedAnswer.value = answer

        if (answer == currentQuestion().correctAnswer) {
            _score.value += 1
        }
    }

    fun nextQuestion() {
        if (_currentIndex.value < question.lastIndex) {
            _currentIndex.value += 1
            _selectedAnswer.value = null
        } else {
            _isFinished.value = true
        }
    }

    fun restart() {
        _currentIndex.value = 0
        _score.value = 0
        _selectedAnswer.value = null
        _isFinished.value = false
    }
}