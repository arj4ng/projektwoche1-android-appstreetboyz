package com.example.projektwohce1_android_appstreetboyz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuotesViewModel
import com.example.projektwohce1_android_appstreetboyz.ui.theme.ProjektWohce1_Android_AppStreetBoyzTheme
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjektWohce1_Android_AppStreetBoyzTheme {
                Appstart(
                    flashcardsViewModel = FlashcardsViewModel(),
                    quotesViewModel = QuotesViewModel(),
                    quizViewModel = QuizViewModel(),
                )
            }
        }
    }
}
