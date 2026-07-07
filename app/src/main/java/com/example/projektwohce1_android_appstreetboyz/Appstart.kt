package com.example.projektwohce1_android_appstreetboyz

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.projektwohce1_android_appstreetboyz.viewmodel.FlashcardsViewModel
import com.example.projektwohce1_android_appstreetboyz.viewmodel.QuotesViewModel

@Composable
fun Appstart(
    flashcardsViewModel: FlashcardsViewModel,
    quotesViewModel: QuotesViewModel
){
    val navController = rememberNavController()
}

@Preview(showSystemUi = true)
@Composable
private fun AppstartPreview() {
    Appstart(
        flashcardsViewModel = FlashcardsViewModel(),
        quotesViewModel = QuotesViewModel()
    )
}