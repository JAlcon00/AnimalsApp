package com.example.animalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar      = { BottomBar(navController) }
                ) { padding ->
                    NavGraph(
                        modifier = Modifier.padding(padding),
                        navController = navController)
                }
            }
        }
    }
}



