package com.example.projektwohce1_android_appstreetboyz.data.model

data class Quote(
    val id: Int,
    val text: String,
    val author: String,
    val category: QuoteCategory
)

enum class QuoteCategory {
    PROGRAMMING_JOKE, MOTIVATIONAL
}
