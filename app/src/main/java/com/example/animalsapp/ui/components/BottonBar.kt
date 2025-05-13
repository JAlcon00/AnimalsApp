package com.example.animalsapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.animalsapp.ui.navigation.Screen
import com.example.animalsapp.ui.theme.DarkGreen
import com.example.animalsapp.ui.theme.PastelYellow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Pets

@Composable
fun CustomBottomBar(navController: NavHostController) {
    val items = listOf(
        Screen.AnimalsList to Icons.Filled.Pets,
        Screen.EnvironmentsList to Icons.Filled.Eco
    )

    // Observa la ruta actual
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Surface(
        color = PastelYellow,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(50),
        tonalElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { (screen, icon) ->
                // Verifica si está seleccionado
                val selected = currentRoute == screen.route
                // Animación de escala para icono
                val iconScale by animateFloatAsState(if (selected) 1.2f else 1f)

                Column(
                    modifier = Modifier
                        .clickable { navController.navigate(screen.route) }
                        .padding(vertical = 4.dp)
                        .width(56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = screen.label,
                        tint = DarkGreen,
                        modifier = Modifier
                            .size((24 * iconScale).dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = screen.label,
                        color = DarkGreen,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

