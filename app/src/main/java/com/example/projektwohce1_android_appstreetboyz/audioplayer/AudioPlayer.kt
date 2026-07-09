package com.example.projektwohce1_android_appstreetboyz.audioplayer

import android.content.Context
import android.media.SoundPool
import com.example.projektwohce1_android_appstreetboyz.R

class AudioPlayer(context: Context) {

    //Hier wird ein SoundPool erstellt.
    // Ein SoundPool ist dafür gedacht, kurze Sounds sehr schnell abzuspielen.
    private val soundPool = SoundPool.Builder().build()


    //Hier werden die Sounds geladen
    private val correct = soundPool.load(context, R.raw.correct, 1)
    private val wrong = soundPool.load(context, R.raw.wrong, 1)
    private val flip = soundPool.load(context, R.raw.flip, 1)
    private val lose = soundPool.load(context, R.raw.rick, 1)
    private val victory = soundPool.load(context, R.raw.takeonme, 1)
    private val perfect = soundPool.load(context, R.raw.iwantitthatway, 1)



    //Hier werden die Sound übergeben

    fun playCorrect() {
        soundPool.play(correct, 1f, 1f, 1, 0, 1f)
    }

    fun playWrong() {
        soundPool.play(wrong, 1f, 1f, 1, 0, 1f)
    }

    fun playFlip() {
        soundPool.play(flip, 1f, 1f, 1, 0, 1f)
    }

    fun playLose() {
        soundPool.play(lose, 0.5f, 0.5f, 1, 0, 0.7f)
    }

    fun playVictory() {
        soundPool.play(victory, 0.5f, 0.5f, 1, 0, 0.8f)
    }

    fun playPerfect() {
        soundPool.play(perfect, 0.5f, 0.5f, 1, 0, 0.8f)
    }
    //SoundPool belegt Speicher,wenn du eine Seite verlässt, brauchst du ihn nicht mehr.

    fun release() {
        soundPool.release()
    }

}

/*
Screens den Audioplayer übergeben: audioPlayer: AudioPlayer


Im Scaffold
    val context = LocalContext.current
    val audioPlayer = remember {
        AudioPlayer(context)
    }
    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.release()
        }
    }

 */

/*

ZUM TESTEN OB DER SOUND FUNKTIONIERT ÜBER DIE EMULATION.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjektWohce1_Android_AppStreetBoyzTheme {

                val audioPlayer = remember {
                    AudioPlayer(this)
                }

                FlashcardScreen(
                    viewModel = FlashcardsViewModel(),
                    onNavigateBack = {},
                    audioPlayer = audioPlayer
                )
            }
        }
    }
}
 */