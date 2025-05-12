package com.example.animalsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
) {
    NavHost(
        navController   = navController,
        startDestination = Screen.AnimalsList.route,
        modifier        = modifier
    ) {
        // Lista de animales (paso posicional)
        composable(Screen.AnimalsList.route) {
            AnimalsListScreen(navController)
        }
        // Lista de ambientes
        composable(Screen.EnvironmentsList.route) {
            EnvironmentsListScreen(navController)
        }
        // Detalle de un animal
        composable(Screen.AnimalDetail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            AnimalDetailScreen(id, navController)
        }
        // Detalle de un ambiente
        composable(Screen.EnvDetail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            EnvDetailScreen(id, navController)
        }
    }
}

