package com.example.animalsapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.animalsapp.ui.navigation.Screen
import com.example.animalsapp.ui.theme.DarkGreen
import com.example.animalsapp.ui.theme.PastelYellow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Eco

@Composable
fun CustomBottomBar(navController: NavHostController) {
    Surface(
        color = PastelYellow,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(50),
        tonalElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(56.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton({ navController.navigate(Screen.AnimalsList.route) }) {
                Icon(Icons.Filled.Pets, contentDescription = "Inicio", tint = DarkGreen)
            }
            Text(text = "Inicio", color = DarkGreen)
            IconButton({ navController.navigate(Screen.EnvironmentsList.route) }) {
                Icon(Icons.Filled.Eco, contentDescription = "Ambientes", tint = DarkGreen)
            }
            Text(text = "Ambientes", color = DarkGreen)
        }
    }
}


