package com.example.projektwohce1_android_appstreetboyz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projektwohce1_android_appstreetboyz.ViewModels.DataSource
import com.example.projektwohce1_android_appstreetboyz.data.model.Flashcard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class FlashcardsViewModel: ViewModel() {

    private val _flashcards = MutableStateFlow(DataSource.flashcards)
    val flashcards: StateFlow<List<Flashcard>> = _flashcards

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex

    private val _masteredCards = MutableStateFlow<List<Flashcard>>(value = emptyList())
    val masteredCards: StateFlow<List<Flashcard>> = _masteredCards

    private val _cardsToRepeat = MutableStateFlow<List<Flashcard>>(value = emptyList())
    val cardsToRepeat: StateFlow<List<Flashcard>> = _cardsToRepeat

    private val _selectedCategory = MutableStateFlow<String?>(value = null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    val filteredFlashcards: StateFlow<List<Flashcard>> = combine(
        flow = _flashcards,
        flow2 = _selectedCategory
    ) { cards, category ->
        if (category == null) cards else cards.filter { it.category == category }
    }.stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = DataSource.flashcards)


    fun selectCategory(category: String?) {
        _selectedCategory.value = category
        _currentIndex.value = 0 // Zurück zum Anfang beim Themenwechsel
    }
    fun previousCard() {
        if (_currentIndex.value > 0) {
            _currentIndex.value -= 1
        }
    }
    fun swipeRight(card: Flashcard) {
        _masteredCards.value += card
        nextCard()
    }
    fun swipeLeft(card: Flashcard) {
        if (!_cardsToRepeat.value.contains(card)) {
            _cardsToRepeat.value += card
        }
        nextCard()
    }
    fun nextCard() {
        if (_currentIndex.value < _flashcards.value.size - 1) {
            _currentIndex.value += 1
        }
    }
    fun markAsMastered(id: Int) {
        //Logik um Krate als gelernt zu markieren
    }
}