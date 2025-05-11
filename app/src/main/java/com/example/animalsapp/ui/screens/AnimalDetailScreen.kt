package com.example.animalsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.horizontalScroll
import com.example.animalsapp.network.RetrofitInstance
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.ui.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import coil3.compose.AsyncImage

@Composable
fun AnimalDetailScreen(id: String, navController: NavHostController) {
    val animalState = remember { mutableStateOf<AnimalsItem?>(null) }

    LaunchedEffect(id) {
        try {
            val item = RetrofitInstance.animalsService.getAnimalById(id)
            animalState.value = item
        } catch (_: Exception) {
            // manejar error si es necesario
        }
    }

    animalState.value?.let { animal ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(text = animal.name)
            Spacer(Modifier.height(12.dp))
            AsyncImage(
                model = animal.image,
                contentDescription = animal.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(text = animal.description)
            Spacer(Modifier.height(16.dp))

            Text(text = "Hechos Interesantes")
            Spacer(Modifier.height(8.dp))
            animal.facts.forEach { fact ->
                Card(modifier = Modifier.padding(vertical = 4.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(Icons.Filled.Info, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(text = fact)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Text(text = "Galería")
            Spacer(Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                animal.imageGallery.forEach { img ->
                    AsyncImage(
                        model = img,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}
