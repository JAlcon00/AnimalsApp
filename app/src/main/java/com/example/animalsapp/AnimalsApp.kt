// app/src/main/java/com/example/animalsapp/AnimalsApp.kt
package com.example.animalsapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.animalsapp.ui.components.BottomBar
import com.example.animalsapp.ui.navigation.NavGraph
import com.example.animalsapp.ui.theme.AnimalsAppTheme

@Composable
fun AnimalsApp() {
    AnimalsAppTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomBar(navController) }
        ) { padding ->
            NavGraph(
                navController = navController,
                modifier      = Modifier.padding(padding)
            )
        }
    }
}


