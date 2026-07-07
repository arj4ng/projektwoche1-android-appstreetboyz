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
            id = 4,
            question = "Welche Funktion startet ein Kotlin-Programm?",
            answer = "Die 'main()' Funktion.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 5,
            question = "Was bewirkt die '@Composable' Annotation?",
            answer = "Sie markiert eine Funktion als UI-Komponente, die vom Compose-Compiler verarbeitet werden kann.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 6,
            question = "Wofür nutzt man 'remember' in Compose?",
            answer = "Um einen Wert während der Recomposition im Speicher zu behalten.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 7,
            question = "Was ist der Unterschied zwischen 'val' und 'var'?",
            answer = "'val' ist schreibgeschützt (unveränderlich), 'var' kann nach der Zuweisung geändert werden.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 8,
            question = "Wie implementiert man eine vertikale Liste von Elementen?",
            answer = "Mit der 'Column' Komponente (oder 'LazyColumn' für große Listen).",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 9,
            question = "Was macht der Elvis-Operator '?:'?",
            answer = "Er liefert einen Standardwert zurück, falls der linke Ausdruck 'null' ist.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 10,
            question = "Was ist ein 'Modifier'?",
            answer = "Ein Objekt, mit dem man das Aussehen, Layout oder Verhalten einer Komponente anpassen kann.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 11,
            question = "Was bedeutet 'Recomposition'?",
            answer = "Der Prozess, bei dem Composable-Funktionen mit neuen Daten erneut ausgeführt werden, um die UI zu aktualisieren.",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 12,
            question = "Wie fügt man einen String-Wert in einen anderen String ein?",
            answer = "Mithilfe von String-Templates und dem '$'-Zeichen (z.B. \"Hallo \$name\").",
            category = "Kotlin Compose"
        ),
        Flashcard(
            id = 13,
            question = "Wie definiert man eine Konstante in Swift?",
            answer = "Mit dem Schlüsselwort 'let'.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 14,
            question = "Was ist ein 'Optional' in Swift?",
            answer = "Ein Datentyp, der einen Wert oder 'nil' enthalten kann.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 15,
            question = "Welches Protokoll muss jede SwiftUI-View erfüllen?",
            answer = "Das 'View' Protokoll, welches eine 'body' Eigenschaft erfordert.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 16,
            question = "Wie deklariert man eine einfache State-Variable in SwiftUI?",
            answer = "Mit dem Property Wrapper '@State'.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 17,
            question = "Was ist das Äquivalent zu Column in SwiftUI?",
            answer = "Der 'VStack' (Vertical Stack).",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 18,
            question = "Wofür steht 'HStack'?",
            answer = "Horizontal Stack, um Elemente nebeneinander anzuordnen.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 19,
            question = "Wie nennt man anonyme Funktionen in Swift?",
            answer = "Closures.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 20,
            question = "Was bewirkt der '.padding()' Modifier?",
            answer = "Er fügt leeren Raum um die View hinzu.",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 21,
            question = "Wie kennzeichnet man eine Variable, die von außen übergeben wird?",
            answer = "Einfach als reguläre 'let' oder 'var' Eigenschaft (Dependency Injection).",
            category = "SwiftUI"
        ),
        Flashcard(
            id = 22,
            question = "Wie heißt das Makro für die Live-Vorschau in Xcode?",
            answer = "#Preview (seit Swift 5.9 / iOS 17).",
            category = "SwiftUI"
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
            id = 4,
            text = "Es gibt 10 Arten von Menschen: Die, die Binärzahlen verstehen, und die, die es nicht tun.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 5,
            text = "Hardware ist der Teil eines Computers, gegen den man treten kann; Software der Teil, auf den man nur schimpfen kann.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 6,
            text = "Warum bevorzugen Programmierer den Dark Mode? Weil Licht Bugs anzieht.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 7,
            text = "Ein SQL-Abfrage geht in eine Bar, geht an zwei Tische heran und fragt: 'Darf ich mich dazugesellen?'",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 8,
            text = "Programmierer: Ein Organismus, der Koffein in Code verwandelt.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 9,
            text = "99 kleine Bugs im Code, 99 kleine Bugs. Nimm einen raus, patch ihn kurz... 127 kleine Bugs im Code.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 10,
            text = "Wie viele Programmierer braucht man, um eine Glühbirne zu wechseln? Keinen, das ist ein Hardware-Problem.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 11,
            text = "Echte Programmierer fangen bei 0 an zu zählen.",
            author = "Unbekannt",
            category = QuoteCategory.PROGRAMMING_JOKE
        ),
        Quote(
            id = 12,
            text = "Ein Programmierer geht in eine Bar und bestellt ein Bier. Dann bestellt er 0 Biere. Dann bestellt er 999999999 Biere. Dann bestellt er eine Eidechse.",
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
        ),
        Quote(
            id = 13,
            text = "Die beste Art, die Zukunft vorherzusagen, ist, sie zu erfinden.",
            author = "Alan Kay",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 14,
            text = "Bleib hungrig, bleib töricht.",
            author = "Steve Jobs",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 15,
            text = "Erfolg ist nicht endgültig, Misserfolg ist nicht fatal: Was zählt, ist der Mut weiterzumachen.",
            author = "Winston Churchill",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 16,
            text = "Tu es oder tu es nicht. Es gibt kein Versuchen.",
            author = "Yoda",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 17,
            text = "Qualität ist keine Tat, sondern eine Gewohnheit.",
            author = "Aristoteles",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 18,
            text = "Es scheint immer unmöglich, bis es vollbracht ist.",
            author = "Nelson Mandela",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 19,
            text = "Glaube daran, dass du es kannst, und du hast die Hälfte des Weges bereits geschafft.",
            author = "Theodore Roosevelt",
            category = QuoteCategory.MOTIVATIONAL
        ),
        Quote(
            id = 20,
            text = "Der einzige Weg, das Unmögliche zu erreichen, ist der Glaube, dass es möglich ist.",
            author = "Charles Kingsleigh",
            category = QuoteCategory.MOTIVATIONAL
        )
    )
}