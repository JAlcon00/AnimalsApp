package com.example.animalsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.animalsapp.network.RetrofitInstance
import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.ui.navigation.Screen

@Composable
fun EnvDetailScreen(id: String, navController: NavHostController) {
    val envState = remember { mutableStateOf<EnviromentItem?>(null) }

    LaunchedEffect(id) {
        try {
            val item = RetrofitInstance.envService.getEnviromentById(id)
            envState.value = item
        } catch (_: Exception) {
            // manejar error si es necesario
        }
    }

    envState.value?.let { env ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(text = env.name)
            Spacer(Modifier.height(12.dp))
            AsyncImage(
                model = env.image,
                contentDescription = env.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(text = env.description)
            Spacer(Modifier.height(16.dp))
            // Lista de habitantes si estuviera disponible
        }
    }
}