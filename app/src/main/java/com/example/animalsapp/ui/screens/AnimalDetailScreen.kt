package com.example.animalsapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
    vm: AnimalDetailViewModel = viewModel()
) {
    LaunchedEffect(id) { vm.fetchAnimal(id) }
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
                CircularProgressIndicator(color = LightGreen)
            }
            is UiState.Error -> Text(
                text = (uiState as UiState.Error).message.orEmpty(),
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
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 26.sp),
                    color = PastelYellow,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))

                // Imagen principal con ligero borde
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(animal.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = animal.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(DarkGreen.copy(alpha = 0.7f))
                )
                Spacer(Modifier.height(16.dp))

                // Descripción
                Text(
                    text = animal.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(24.dp))

                // Hechos
                Text(
                    text = "Hechos Interesantes",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                    color = PastelYellow,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                animal.facts.forEach { fact ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkGreen.copy(alpha = 0.85f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .animateContentSize(animationSpec = tween(350))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = PastelYellow,
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(PastelYellow.copy(alpha = 0.2f), CircleShape)
                                    .padding(4.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = fact,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))

                // Galería refinada
                Text(
                    text = "Galería",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                    color = PastelYellow,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))

                var selectedIndex by remember { mutableStateOf(0) }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    itemsIndexed(animal.imageGallery) { index, url ->
                        val isSelected = index == selectedIndex
                        val scale by animateFloatAsState(
                            targetValue = if (isSelected) 1.1f else 0.9f,
                            animationSpec = tween(300)
                        )
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .size((80 * scale).dp)
                                .clickable { selectedIndex = index }
                                .clip(CircleShape),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) LightGreen else DarkGreen.copy(alpha = 0.6f)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 4.dp)
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(url)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}