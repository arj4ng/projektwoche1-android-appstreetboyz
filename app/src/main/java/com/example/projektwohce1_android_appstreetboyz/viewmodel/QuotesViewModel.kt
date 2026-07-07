package com.example.projektwohce1_android_appstreetboyz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projektwohce1_android_appstreetboyz.ViewModels.DataSource
import com.example.projektwohce1_android_appstreetboyz.data.model.Quote
import com.example.projektwohce1_android_appstreetboyz.data.model.QuoteCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class QuotesViewModel: ViewModel() {
    private val _allQuotes = MutableStateFlow(DataSource.quotes)
    private val _selectedCategory = MutableStateFlow<QuoteCategory?>(null)
    private val _favoriteQuoteIds = MutableStateFlow<Set<Int>>(emptySet())
    private val _showFavoritesOnly = MutableStateFlow(false)

    val selectedCategory: StateFlow<QuoteCategory?> = _selectedCategory
    val favoriteQuoteIds: StateFlow<Set<Int>> = _favoriteQuoteIds
    val showFavoritesOnly: StateFlow<Boolean> = _showFavoritesOnly
    val categories: List<QuoteCategory> = DataSource.quotes.map { it.category }.distinct()

    val quotes: StateFlow<List<Quote>> = combine(
        _allQuotes,
        _selectedCategory,
        _favoriteQuoteIds,
        _showFavoritesOnly
    ) { quotes, category, favoriteIds, showFavoritesOnly ->
        quotes
            .filter { category == null || it.category == category }
            .filter { !showFavoritesOnly || it.id in favoriteIds }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DataSource.quotes
    )

    fun selectCategory(category: QuoteCategory?) {
        _selectedCategory.value = category
    }

    fun toggleFavorite(quoteId: Int) {
        _favoriteQuoteIds.value = if (quoteId in _favoriteQuoteIds.value) {
            _favoriteQuoteIds.value - quoteId
        } else {
            _favoriteQuoteIds.value + quoteId
        }
    }

    fun toggleFavoritesOnly() {
        _showFavoritesOnly.value = !_showFavoritesOnly.value
    }
}
