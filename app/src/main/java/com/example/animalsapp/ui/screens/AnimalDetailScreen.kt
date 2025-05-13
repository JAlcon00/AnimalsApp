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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
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
import com.example.animalsapp.ui.theme.Dimens
import com.example.animalsapp.ui.theme.LightGreen
import com.example.animalsapp.ui.theme.PastelYellow
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
    val uiState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        when (uiState) {
            is UiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(color = PastelYellow)
            }
            is UiState.Error -> {
                Text(
                    text = (uiState as UiState.Error).message ?: "Error",
                    color = LightGreen,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
            is UiState.Success -> {
                val animal = (uiState as UiState.Success<AnimalsItem>).data

                Text(
                    text = animal.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = PastelYellow
                )
                Spacer(Modifier.height(12.dp))

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
                Text(
                    text = animal.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = PastelYellow
                )

                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Hechos Interesantes",
                    style = MaterialTheme.typography.titleMedium,
                    color = PastelYellow
                )
                Spacer(Modifier.height(8.dp))

                animal.facts.forEach { fact ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = LightGreen.copy(alpha = 0.2f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.Info, contentDescription = null, tint = DarkGreen)
                            Spacer(Modifier.width(8.dp))
                            Text(fact, style = MaterialTheme.typography.bodyMedium, color = DarkGreen)
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Galería",
                    style = MaterialTheme.typography.titleMedium,
                    color = PastelYellow
                )
                Spacer(Modifier.height(8.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
            }
        }
    }
}
