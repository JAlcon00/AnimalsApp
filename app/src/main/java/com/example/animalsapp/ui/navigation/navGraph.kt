// app/src/main/java/com/example/animalsapp/ui/navigation/NavGraph.kt
package com.example.animalsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier          // <- Import necesario
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animalsapp.ui.screens.AnimalsListScreen
import com.example.animalsapp.ui.screens.EnvironmentsListScreen
import com.example.animalsapp.ui.screens.AnimalDetailScreen
import com.example.animalsapp.ui.screens.EnvDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier    // <- Parámetro opcional
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AnimalsList.route,
        modifier = modifier            // <- Aplica el padding aquí
    ) {
        composable(Screen.AnimalsList.route) {
            AnimalsListScreen(navController)
        }
        composable(Screen.EnvironmentsList.route) {
            EnvironmentsListScreen(navController)
        }
        composable(Screen.AnimalDetail.route) { back ->
            val id = back.arguments?.getString("id") ?: return@composable
            AnimalDetailScreen(id, navController)
        }
        composable(Screen.EnvDetail.route) { back ->
            val id = back.arguments?.getString("id") ?: return@composable
            EnvDetailScreen(id, navController)
        }
    }
}
