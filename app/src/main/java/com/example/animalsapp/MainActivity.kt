package com.example.animalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.animalsapp.ui.components.BottomBar
import com.example.animalsapp.ui.navigation.NavGraph
import com.example.animalsapp.ui.theme.AnimalsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalsAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController) }
                ) { padding ->
                    NavGraph(
                        navController = navController,
                        paddingValues = PaddingValues(paddingValues = padding)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimalsAppTheme(
    content: @Composable () -> Unit
) {
    AnimalsAppTheme {
        content()
    }
}
