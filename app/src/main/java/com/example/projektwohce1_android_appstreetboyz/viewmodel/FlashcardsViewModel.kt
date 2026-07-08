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

    // Track which cards have been swiped in the current normal session
    private val _sessionSwipedIds = MutableStateFlow<Set<Int>>(emptySet())

    val filteredFlashcards: StateFlow<List<Flashcard>> = combine(
        _flashcards,
        _selectedCategory,
        _isRepeatMode,
        _cardsToRepeat,
        _sessionSwipedIds
    ) { cards, category, isRepeat, repeatCards, swipedIds ->
        if (isRepeat) {
            repeatCards
        } else {
            val baseCards = if (category == null) cards else cards.filter { it.category == category }
            // In normal mode, remove cards that were already swiped in this session
            baseCards.filter { it.id !in swipedIds }
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun setRepeatMode(active: Boolean) {
        _isRepeatMode.value = active
        _currentIndex.value = 0
        _sessionSwipedIds.value = emptySet() // Resetet die Session modus geändert

    }


    fun selectCategory(category: String?) {
        _selectedCategory.value = category
        _currentIndex.value = 0 // Zurück zum Anfang beim Themenwechsel
        _sessionSwipedIds.value = emptySet() // Reset session when changing category
    }

    fun previousCard() {
        if (_currentIndex.value > 0) {
            _currentIndex.value -= 1
        }
    }

    fun swipeRight(card: Flashcard) {
        if (_isRepeatMode.value) {
            // Aus der Wiederholungsliste löschen
            val updatedList = _cardsToRepeat.value.filter { it.id != card.id }
            _cardsToRepeat.value = updatedList

            // Index korrigieren: Wenn wir am Ende sind, zurück auf 0 oder eins zurück
            if (updatedList.isEmpty()) {
                _currentIndex.value = 0
            } else if (_currentIndex.value >= updatedList.size) {
                _currentIndex.value = updatedList.size - 1
            }
        } else {
            // Normaler Modus: Karte als gelernt markieren und aus der Session entfernen
            _masteredCards.value += card
            _sessionSwipedIds.value += card.id

            // Da die Liste schrumpft, versuchen wir beim Index 0 zu bleiben (die nächste Karte rückt nach)
            // Nur wenn wir wirklich am Ende sind, setzen wir zurück.
            if (_currentIndex.value >= (filteredFlashcards.value.size - 1)) {
                _currentIndex.value = 0
            }
        }
    }

    fun swipeLeft(card: Flashcard) {
        if (_isRepeatMode.value) {
            // Karte bleibt in der Liste, wir springen zur nächsten Karte (Loop)
            val currentSize = filteredFlashcards.value.size
            if (currentSize > 0) {
                _currentIndex.value = (_currentIndex.value + 1) % currentSize
            }
        } else {
            // Normaler Modus: Karte zur Wiederholung hinzufügen und aus der Session entfernen
            if (!_cardsToRepeat.value.contains(card)) {
                _cardsToRepeat.value += card
            }
            _sessionSwipedIds.value += card.id

            if (_currentIndex.value >= (filteredFlashcards.value.size - 1)) {
                _currentIndex.value = 0
            }
        }
    }
}
