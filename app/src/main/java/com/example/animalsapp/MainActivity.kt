package com.example.animalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.animalsapp.ui.theme.AnimalsAppTheme
import com.example.animalsapp.ui.theme.DarkGreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1) Decora la ventana para dibujar tras status & nav bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AnimalsAppTheme {
                // 2) Box que llena TODO el tamaño y dibuja el fondo
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = DarkGreen)               // tu color raíz
                        .systemBarsPadding()                         // respeta status+nav bars
                ) {
                    AnimalsApp()                                   // tu NavHost / Scaffold
                }
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    AnimalsApp()
}






