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
    private val _isRepeatMode = MutableStateFlow(false)
    val isRepeatMode: StateFlow<Boolean> = _isRepeatMode

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
        flow2 = _selectedCategory,
        flow3 = _isRepeatMode
    ) { cards, category, isRepeat  ->
        if (isRepeat) {
            _cardsToRepeat.value //Wenn WiederholungsModus nur diese zeigen
        } else if (category == null) {
            cards //Alle zeigen
        } else {
            cards.filter { it.category == category } //Nach Thema filtern
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = DataSource.flashcards
    )

    fun setRepeatMode(active: Boolean) {
        _isRepeatMode.value = active
        _currentIndex.value = 0
    }


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
       if (_isRepeatMode.value) {
           //Löschen
           _cardsToRepeat.value = _cardsToRepeat.value.filter { it.id != card.id }
           //sicherhaltshalber anpassen falls wir am Ende der Liste sind
           if(_currentIndex.value >= _cardsToRepeat.value.size && _currentIndex.value > 0) {
               _currentIndex.value -= 1
           }
       } else {
           _masteredCards.value += card
           nextCard()
       }
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