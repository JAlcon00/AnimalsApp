package com.example.animalsapp.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.ui.theme.DarkGreen
import com.example.animalsapp.ui.theme.LightGreen
import com.example.animalsapp.ui.theme.PastelYellow
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.AnimalDetailViewModel

@Composable
fun AnimalDetailScreen(
    id: String,
    navController: NavHostController,
    vm: AnimalDetailViewModel = viewModel(),

    ) {
    val uiState by vm.uiState.collectAsState()

    LaunchedEffect(id) {
        vm.fetchAnimal(id)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGreen
    ) {
        when (uiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PastelYellow)
                }
            }
            is UiState.Error -> {
                val msg = (uiState as UiState.Error).message
                Box(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: $msg",
                        color = PastelYellow,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            is UiState.Success<*> -> {
                val animal = (uiState as UiState.Success<AnimalsItem>).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    // Título
                    Text(
                        text = animal.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = PastelYellow
                    )
                    Spacer(Modifier.height(12.dp))

                    // Imagen principal
                    AsyncImage(
                        model = animal.image,
                        contentDescription = animal.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(2.dp, LightGreen, RoundedCornerShape(16.dp))
                    )
                    Spacer(Modifier.height(12.dp))

                    // Descripción
                    Text(
                        text = animal.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = PastelYellow
                    )
                    Spacer(Modifier.height(20.dp))

                    // Hechos interesantes
                    Text(
                        text = "Hechos Interesantes",
                        style = MaterialTheme.typography.titleMedium,
                        color = PastelYellow
                    )
                    Spacer(Modifier.height(8.dp))
                    animal.facts.forEach { fact ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = LightGreen.copy(alpha = 0.2f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text(
                                    text = "•",
                                    color = DarkGreen,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = fact,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = DarkGreen
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))

                    // Galería
                    Text(
                        text = "Galería",
                        style = MaterialTheme.typography.titleMedium,
                        color = PastelYellow
                    )
                    Spacer(Modifier.height(8.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(animal.imageGallery) { imgUrl ->
                            AsyncImage(
                                model = imgUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, LightGreen, CircleShape)
                            )
                        }
                    }

                    Spacer(Modifier.height(80.dp)) // espacio para el bottom bar
                }
            }
        }
    }
}