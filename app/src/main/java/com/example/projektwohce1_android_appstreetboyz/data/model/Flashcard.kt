package com.example.projektwohce1_android_appstreetboyz.data.model

data class Flashcard(
    val id: Int,
    val question: String,
    val answer: String,
    val category: String, //kotlin/swift
    var isMastered: Boolean = false
){}
