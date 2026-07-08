<h1 align="center">QuoteCraft</h1>

<p align="center">
  Kleine Lern-App mit <strong>Quotes</strong>, <strong>Flashcards</strong> und vorbereitetem <strong>Quiz</strong>.<br/>
  Entwickelt mit <strong>Kotlin</strong>, <strong>Jetpack Compose</strong> und einer einfachen <strong>MVVM-Struktur</strong>.
</p>

<hr/>

<h2>Über das Projekt</h2>

<p>
  QuoteCraft ist aus einer Teamarbeit entstanden und kombiniert mehrere kleine Lern- und Motivationsideen in einer App.
  Ziel war kein überladenes Produkt, sondern eine Anwendung, die sich leicht anfühlt und trotzdem mehrere Funktionen
  an einem Ort bündelt.
</p>

<p>
  Aktuell liegt der Fokus auf drei Bereichen:
</p>

<ul>
  <li><strong>Home</strong> als Startpunkt mit kurzer Übersicht</li>
  <li><strong>Flashcards</strong> zum aktiven Lernen per Karteikarten</li>
  <li><strong>Quotes</strong> zum Stöbern, Filtern und Markieren von Favoriten</li>
</ul>

<p>
  Das <strong>Quiz</strong> ist daten- und logikseitig bereits vorbereitet und als eigener Screen vorhanden,
  ist aber im aktuellen Stand noch nicht vollständig in den Hauptfluss der App eingebunden.
</p>

<hr/>

<h2>Was aktuell schon funktioniert</h2>

<h3>Home-Screen</h3>
<ul>
  <li>Begrüßung beim Start der App</li>
  <li>Anzeige eines täglichen Zitats aus dem vorhandenen Datensatz</li>
  <li>Kurze Übersicht über vorhandene Lernkarten</li>
  <li>Button zum Wechsel in den Lernbereich</li>
</ul>

<h3>Flashcards</h3>
<ul>
  <li>Themenbasierte Lernkarten aus dem lokalen <code>DataSource</code>-Datensatz</li>
  <li>Kategorien wie <em>Kotlin Compose</em> und <em>SwiftUI</em></li>
  <li>Karten lassen sich drehen</li>
  <li>Swipe-Logik für gelernt / wiederholen</li>
  <li>Fortschritt über den aktuellen Kartenindex</li>
  <li>Optionaler Audio-Hook beim Flippen der Karte</li>
</ul>

<h3>Quotes</h3>
<ul>
  <li>Anzeige von Zitaten in einer klaren Kartenansicht</li>
  <li>Filter nach Kategorien</li>
  <li>Favoritenfunktion über Herzsymbol pro Quote</li>
  <li>Eigener Favoriten-Button oben rechts zum Laden nur der Lieblingszitate</li>
</ul>

<h3>Quiz</h3>
<ul>
  <li>Fragenmodell für Multiple Choice und Wahr/Falsch vorhanden</li>
  <li>Quiz-Daten bereits im <code>DataSource</code> angelegt</li>
  <li><code>QuizViewModel</code> verwaltet Fragenindex, Auswahl, Punktezahl und Neustart</li>
  <li><code>QuizScreen</code> ist vorbereitet und zeigt Fragen, Antworten und Ergebnisansicht</li>
</ul>

<p>
  <strong>Wichtig:</strong> Der Quiz-Bereich ist technisch schon deutlich weiter als “leer”, aber aktuell noch nicht
  sauber in die Hauptnavigation integriert.
</p>

<hr/>

<h2>Technischer Stand</h2>

<ul>
  <li><strong>Sprache:</strong> Kotlin</li>
  <li><strong>UI:</strong> Jetpack Compose + Material 3</li>
  <li><strong>Architektur:</strong> MVVM</li>
  <li><strong>State:</strong> <code>StateFlow</code> / <code>MutableStateFlow</code></li>
  <li><strong>Navigation:</strong> Navigation Compose</li>
  <li><strong>Datenquelle:</strong> lokale statische Daten über <code>DataSource</code></li>
  <li><strong>Min SDK:</strong> 24</li>
</ul>

<hr/>

<h2>Projektstruktur</h2>

<pre><code>app/src/main/java/com/example/projektwohce1_android_appstreetboyz/
├── data/
│   ├── DataSource.kt
│   ├── Screen.kt
│   └── model/
├── viewmodel/
│   ├── FlashcardsViewModel.kt
│   ├── QuotesViewModel.kt
│   └── QuizViewModel.kt
├── ui/
│   ├── screens/
│   │   ├── home/
│   │   ├── flashcards/
│   │   ├── quotes/
│   │   └── quiz/
│   └── theme/
├── Appstart.kt
└── MainActivity.kt
</code></pre>

<hr/>

<h2>Was an der App sympathisch macht</h2>

<p>
  QuoteCraft versucht nicht, alles gleichzeitig zu sein.
  Die App mischt Motivation, Lernen und kleine Interaktionen in einer Form,
  die sich eher wie ein Studienprojekt mit Persönlichkeit anfühlt als wie ein generischer Demo-Clone.
</p>

<p>
  Gerade die Kombination aus Lernkarten, Zitaten und einem vorbereiteten Quiz zeigt,
  dass hier Schritt für Schritt an einer kleinen Lernplattform gearbeitet wurde.
</p>

<hr/>

<h2>Nächste sinnvolle Schritte</h2>

<ul>
  <li>Quiz vollständig in die Hauptnavigation einbinden</li>
  <li><code>MainActivity</code> auf den echten App-Einstieg umstellen bzw. final bereinigen</li>
  <li>Favoriten optional persistent speichern</li>
  <li>UI weiter vereinheitlichen</li>
  <li>Tests für ViewModels und zentrale User-Flows ergänzen</li>
</ul>

<hr/>

<h2>Fazit</h2>

<p>
  Der aktuelle Stand von QuoteCraft ist bereits gut greifbar:
  <strong>Home</strong>, <strong>Flashcards</strong> und <strong>Quotes</strong> liefern schon echte Interaktion,
  während das <strong>Quiz</strong> als nächster logischer Ausbauschritt bereitsteht.
</p>

<p>
  Für ein Teamprojekt ist das eine starke Grundlage, auf der man sauber weiterbauen kann.
</p>
