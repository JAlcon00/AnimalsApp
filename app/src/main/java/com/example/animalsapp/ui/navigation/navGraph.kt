package com.example.animalsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animalsapp.ui.screens.AnimalsListScreen
import com.example.animalsapp.ui.screens.EnvironmentsListScreen
import com.example.animalsapp.ui.screens.AnimalDetailScreen
import com.example.animalsapp.ui.screens.EnvDetailScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.AnimalsList.route
    ) {
        composable(Screen.AnimalsList.route) {
            AnimalsListScreen(navController)
        }
        composable(Screen.EnvironmentsList.route) {
            EnvironmentsListScreen(navController)
        }
        composable(Screen.AnimalDetail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            AnimalDetailScreen(id = id, navController = navController)
        }
        composable(Screen.EnvDetail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            EnvDetailScreen(id = id, navController = navController)
        }
    }
}
