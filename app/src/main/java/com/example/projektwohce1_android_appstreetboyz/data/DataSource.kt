package com.example.projektwohce1_android_appstreetboyz.ViewModels

import com.example.projektwohce1_android_appstreetboyz.data.model.Flashcard
import com.example.projektwohce1_android_appstreetboyz.data.model.Quote
import com.example.projektwohce1_android_appstreetboyz.data.model.QuoteCategory

object DataSource {
    val flashcards = listOf(
        Flashcard(
            id = 1,
            question = "Was ist eine 'val' in Kotlin?",
            answer = "Eine unveränderliche (read-only) Variable.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 2,
            question = "Wie definiert man eine Konstante in Swift?",
            answer = "Mit dem Schlüsselwort 'let'.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 3,
            question = "Was ist ein 'Optional' in Swift?",
            answer = "Ein Datentyp, der einen Wert oder 'nil' enthalten kann.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 4,
            question = "Welche Funktion startet ein Kotlin-Programm?",
            answer = "Die 'main()' Funktion.",
            category = "Kotlin Compose"
        )
    )
    val categories = flashcards.map { it.category }.distinct()//distinct löscht alles doppelte
    val quotes = listOf(
        Quote(
            id = 1,
            text = "Programmieren ist 10% schreiben und 90% herausfinden, warum es nicht geht.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 2,
            text = "Just keep going. Everybody gets better if they keep at it.",
            author = "Denzel Washington",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 3,
            text = "Der einzige Weg, großartige Arbeit zu leisten, ist zu lieben, was man tut.",
            author = "Steve Jobs",
            category = QuoteCategory.MOTIVATIONAL
        )
    )
}