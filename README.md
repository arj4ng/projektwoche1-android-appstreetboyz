<div align="center">

# AppStreetBoyz Learning App

<p>
  <strong>Modern Android study app</strong><br/>
  Flashcards, quotes, quiz, audio feedback, repeat flows, favorites, dark mode.
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
      <h2>What Project Is</h2>
      <p>
        AppStreetBoyz is compact learning app built in <strong>Kotlin</strong> with <strong>Jetpack Compose</strong>.
        It mixes three core study modes into one flow:
      </p>
      <ul>
        <li><strong>Flashcards</strong> for active recall</li>
        <li><strong>Quotes</strong> for short motivation and saved favorites</li>
        <li><strong>Quiz</strong> for fast knowledge checks with sound and result feedback</li>
      </ul>
      <p>
        Project started as team app and now has more polished UI direction:
        stronger typography, custom color theme, modern cards, cleaner dashboards, and better dark mode handling.
      </p>
    </td>
    <td width="48%" valign="top">
      <h2>Highlights</h2>
      <ul>
        <li>Custom light + dark theme</li>
        <li>Modern home dashboard</li>
        <li>Swipe flashcards with repeat logic</li>
        <li>Quote filters + favorites</li>
        <li>Quiz with animations, sounds, confetti</li>
        <li>Bottom navigation app shell</li>
        <li>StateFlow-based MVVM structure</li>
      </ul>
    </td>
  </tr>
</table>

<hr/>

<h2>Feature Overview</h2>

<h3>Home</h3>
<ul>
  <li>Hero dashboard entry screen</li>
  <li>Quick stats for total cards and repeat cards</li>
  <li>Daily quote section</li>
  <li>Fast jump into learning or repeat flow</li>
</ul>

<h3>Flashcards</h3>
<ul>
  <li>Topic-based study cards from local data</li>
  <li>Tap to flip question ↔ answer</li>
  <li>Swipe right = learned</li>
  <li>Swipe left = send to repeat queue</li>
  <li>Repeat mode for missed cards</li>
  <li>Audio feedback on card flip</li>
  <li>Dark-mode-safe swipe colors and readable text</li>
</ul>

<h3>Quotes</h3>
<ul>
  <li>Modern quote browser</li>
  <li>Category filter chips</li>
  <li>Favorite toggle with local in-memory state</li>
  <li>Dedicated favorites view</li>
</ul>

<h3>Quiz</h3>
<ul>
  <li>Multiple choice + true/false questions</li>
  <li>Animated question transitions</li>
  <li>Correct / wrong / next / result sounds</li>
  <li>Perfect-score confetti result state</li>
  <li>Score tracking and restart flow</li>
</ul>

<hr/>

<h2>Tech Stack</h2>

<table>
  <tr><td><strong>Language</strong></td><td>Kotlin</td></tr>
  <tr><td><strong>UI</strong></td><td>Jetpack Compose + Material 3</td></tr>
  <tr><td><strong>Architecture</strong></td><td>MVVM</td></tr>
  <tr><td><strong>State</strong></td><td>StateFlow / MutableStateFlow</td></tr>
  <tr><td><strong>Navigation</strong></td><td>Navigation Compose</td></tr>
  <tr><td><strong>Data</strong></td><td>Local static DataSource</td></tr>
  <tr><td><strong>Audio</strong></td><td>Custom AudioPlayer hooks</td></tr>
  <tr><td><strong>Effects</strong></td><td>Konfetti for perfect quiz result</td></tr>
  <tr><td><strong>Min SDK</strong></td><td>24</td></tr>
</table>

<hr/>

<h2>Project Structure</h2>

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

<h2>Run Project</h2>

<ol>
  <li>Open project in <strong>Android Studio</strong></li>
  <li>Sync Gradle</li>
  <li>Run on emulator or physical Android device</li>
</ol>

<p>
  Main app flow starts from <code>Appstart.kt</code>.
</p>

<hr/>

<h2>Why This Project Stands Out</h2>

<p>
  This is not just plain classroom CRUD app.
  It has motion, interaction, themed UI, sound feedback, repeat learning logic, and multiple study surfaces inside one small Android app.
</p>

<p>
  Biggest strength = app feels like real product direction, not just disconnected demo screens.
</p>

<hr/>

<h2>Next Good Upgrades</h2>

<ul>
  <li>Persist favorites and progress locally</li>
  <li>Add tests for ViewModels and key flows</li>
  <li>Unify top bar / bottom nav styling even more</li>
  <li>Add onboarding or empty-state illustrations</li>
  <li>Track streaks or study sessions</li>
</ul>

<hr/>

<div align="center">
  <strong>Built with Kotlin, Compose, and too many UI polish passes.</strong>
</div>
