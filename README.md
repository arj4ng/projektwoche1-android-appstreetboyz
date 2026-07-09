<div align="center">

# AppStreetBoyz Lern-App

<p>
  <strong>Moderne Android-Lern-App</strong><br/>
  Flashcards, Quotes, Quiz, Audio-Feedback, Wiederholungslogik, Favoriten, Dark Mode.
</p>

<p>
  <img src="https://img.shields.io/badge/Kotlin-2D6BFF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-6B5BD2?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose"/>
  <img src="https://img.shields.io/badge/Material%203-FF8A3D?style=for-the-badge&logo=materialdesign&logoColor=white" alt="Material 3"/>
  <img src="https://img.shields.io/badge/MVVM-12141C?style=for-the-badge&logo=android&logoColor=white" alt="MVVM"/>
</p>

</div>

<br/>

<table>
  <tr>
    <td width="52%" valign="top">
      <h2>Was Projekt Ist</h2>
      <p>
        AppStreetBoyz ist kleine Lern-App in <strong>Kotlin</strong> mit <strong>Jetpack Compose</strong>.
        Sie verbindet drei Hauptbereiche in einem Flow:
      </p>
      <ul>
        <li><strong>Flashcards</strong> für aktives Lernen</li>
        <li><strong>Quotes</strong> für kurze Motivation und Favoriten</li>
        <li><strong>Quiz</strong> für schnelle Wissensabfragen mit Sound und Ergebnis-Feedback</li>
      </ul>
      <p>
        Projekt stammt aus Teamarbeit und hat inzwischen deutlich stärkeren UI-Fokus:
        eigene Farben, bewusstere Typografie, moderne Kartenlayouts, besserer Dark Mode und klarere Screen-Hierarchie.
      </p>
    </td>
    <td width="48%" valign="top">
      <h2>Highlights</h2>
      <ul>
        <li>Eigenes Light- und Dark-Theme</li>
        <li>Moderner Home-Dashboard-Screen</li>
        <li>Swipe-Flashcards mit Wiederholungslogik</li>
        <li>Quotes mit Filtern und Favoriten</li>
        <li>Quiz mit Animationen, Sounds und Konfetti</li>
        <li>Bottom Navigation als App-Shell</li>
        <li>MVVM mit StateFlow</li>
      </ul>
    </td>
  </tr>
</table>

<hr/>

<h2>Feature-Überblick</h2>

<h3>Home</h3>
<ul>
  <li>Dashboard-artiger Einstieg</li>
  <li>Schnelle Übersicht über Karten und Wiederholungen</li>
  <li>Tagesquote direkt auf dem Startscreen</li>
  <li>Soforter Einstieg in Lernen oder Repeat-Modus</li>
</ul>

<h3>Flashcards</h3>
<ul>
  <li>Themenbasierte Lernkarten aus lokaler Datenquelle</li>
  <li>Tap zum Drehen von Frage ↔ Antwort</li>
  <li>Swipe nach rechts = gelernt</li>
  <li>Swipe nach links = für Wiederholung merken</li>
  <li>Eigener Wiederholungsmodus für Fehlerkarten</li>
  <li>Audio-Feedback beim Flippen</li>
  <li>Dark-Mode-sichere Swipe-Farben mit lesbarem Text</li>
</ul>

<h3>Quotes</h3>
<ul>
  <li>Moderner Quotes-Bereich</li>
  <li>Kategorie-Filter</li>
  <li>Favoritenfunktion pro Quote</li>
  <li>Eigene Favoritenansicht</li>
</ul>

<h3>Quiz</h3>
<ul>
  <li>Multiple Choice und Wahr/Falsch</li>
  <li>Animierte Fragewechsel</li>
  <li>Sound für richtig, falsch, weiter und Ergebnis</li>
  <li>Konfetti bei perfektem Ergebnis</li>
  <li>Punkte- und Neustart-Logik</li>
</ul>

<hr/>

<h2>Technik</h2>

<table>
  <tr><td><strong>Sprache</strong></td><td>Kotlin</td></tr>
  <tr><td><strong>UI</strong></td><td>Jetpack Compose + Material 3</td></tr>
  <tr><td><strong>Architektur</strong></td><td>MVVM</td></tr>
  <tr><td><strong>State</strong></td><td>StateFlow / MutableStateFlow</td></tr>
  <tr><td><strong>Navigation</strong></td><td>Navigation Compose</td></tr>
  <tr><td><strong>Daten</strong></td><td>Lokale statische DataSource</td></tr>
  <tr><td><strong>Audio</strong></td><td>Custom AudioPlayer</td></tr>
  <tr><td><strong>Effekte</strong></td><td>Konfetti für perfektes Quiz-Ergebnis</td></tr>
  <tr><td><strong>Min SDK</strong></td><td>24</td></tr>
</table>

<hr/>

<h2>Projektstruktur</h2>

<pre><code>app/src/main/java/com/example/projektwohce1_android_appstreetboyz/
├── audioplayer/
├── data/
│   ├── model/
│   └── DataSource.kt
├── ui/
│   ├── screens/
│   │   ├── home/
│   │   ├── flashcards/
│   │   ├── quotes/
│   │   └── quiz/
│   └── theme/
├── viewmodel/
│   ├── FlashcardsViewModel.kt
│   ├── QuotesViewModel.kt
│   └── QuizViewModel.kt
├── Appstart.kt
└── MainActivity.kt
</code></pre>

<hr/>

<h2>Projekt starten</h2>

<ol>
  <li>Projekt in <strong>Android Studio</strong> öffnen</li>
  <li>Gradle synchronisieren</li>
  <li>App auf Emulator oder Android-Gerät starten</li>
</ol>

<p>
  Haupteinstieg liegt in <code>Appstart.kt</code>.
</p>

<hr/>

<h2>Warum Projekt auffällt</h2>

<p>
  Das hier ist nicht nur einfache Demo-App.
  Projekt hat Interaktion, Bewegung, Audio, Lernlogik, Favoriten und mehrere Screens mit gemeinsamer visueller Richtung.
</p>

<p>
  Größte Stärke:
  App fühlt sich inzwischen eher wie kleines Produkt an und nicht wie lose Sammlung einzelner Compose-Übungen.
</p>

<hr/>

<h2>Sinnvolle nächste Schritte</h2>

<ul>
  <li>Favoriten und Lernfortschritt lokal speichern</li>
  <li>Tests für ViewModels und Hauptflows ergänzen</li>
  <li>Top Bar und Bottom Nav weiter angleichen</li>
  <li>Onboarding oder Empty States ergänzen</li>
  <li>Streaks oder Session-Tracking einbauen</li>
</ul>

<hr/>

<div align="center">
  <strong>Gebaut mit Kotlin, Compose und viel UI-Polish.</strong>
</div>
