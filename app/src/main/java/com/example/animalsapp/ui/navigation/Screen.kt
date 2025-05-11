// app/src/main/java/com/example/animalsapp/ui/navigation/Screen.kt
package com.example.animalsapp.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Eco


sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object AnimalsList     : Screen("animals_list",     Icons.Filled.Pets, "Animales")
    object EnvironmentsList: Screen("env_list",         Icons.Filled.Eco,  "Ambientes")
    object AnimalDetail    : Screen("animal_detail/{id}", Icons.Filled.Pets, "DetalleAnimal")
    object EnvDetail       : Screen("env_detail/{id}",    Icons.Filled.Eco,  "DetalleAmbiente")

    fun createRoute(id: String): String = route.replace("{id}", id)
}



