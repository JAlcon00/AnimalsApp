package com.example.animalsapp.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.ui.theme.DarkGreen
import com.example.animalsapp.ui.theme.PastelYellow
import com.example.animalsapp.ui.theme.LightGreen
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.AnimalDetailViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info

@Composable
fun AnimalDetailScreen(
    id: String,
    navController: NavHostController,
    vm: AnimalDetailViewModel = viewModel()
) {
    // Carga inicial de datos
    LaunchedEffect(id) {
        vm.fetchAnimal(id)
    }

    val uiState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        when (uiState) {
            is UiState.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PastelYellow)
            }

            is UiState.Error -> Text(
                text = (uiState as UiState.Error).message ?: "Error",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                textAlign = TextAlign.Center
            )

            is UiState.Success<*> -> {
                val animal = (uiState as UiState.Success<AnimalsItem>).data

                // Título
                Text(
                    text = animal.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp),
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))

                // Imagen principal
                AsyncImage(
                    model = animal.image,
                    contentDescription = animal.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(Modifier.height(12.dp))

                // Descripción
                Text(
                    text = animal.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))

                // Hechos
                Text(
                    text = "Hechos Interesantes",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                    color = PastelYellow,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))

                // Tarjetas de hechos
                animal.facts.forEach { fact ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = DarkGreen.copy(alpha = 0.8f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(PastelYellow),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = null,
                                    tint = DarkGreen
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = fact,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))

                // Galería
                Text(
                    text = "Galería",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                    color = PastelYellow,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(animal.imageGallery) { url ->
                        AsyncImage(
                            model = url,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .border(2.dp, PastelYellow, CircleShape)
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}