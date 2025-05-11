package com.example.animalsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.animalsapp.network.RetrofitInstance
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.ui.navigation.Screen

@Composable
fun AnimalsListScreen(navController: NavHostController) {
    val animals = remember { mutableStateListOf<AnimalsItem>() }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.animalsService.getAnimals()
            animals.addAll(response)
        } catch (_: Exception) {
            // manejar error si es necesario
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(animals) { animal ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.AnimalDetail.createRoute(animal.id)) }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = animal.image,
                    contentDescription = animal.name,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(text = animal.name)
            }
        }
    }
}