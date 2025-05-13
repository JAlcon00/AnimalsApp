package com.example.animalsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.ui.theme.DarkGreen
import com.example.animalsapp.ui.theme.LightGreen
import com.example.animalsapp.ui.theme.PastelYellow
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.EnvDetailViewModel

@Composable
fun EnvDetailScreen(
    id: String,
    navController: NavHostController,
    vm: EnvDetailViewModel = viewModel()
) {
    // Disparar carga apenas se compone
    LaunchedEffect(id) {
        vm.fetchEnvironment(id)
        vm.fetchAnimals(id)
    }

    // Observar estados
    val envState      by vm.uiState.collectAsState()
    val animalsState by vm.animalsState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(DarkGreen)            // fondo toda la pantalla
            .padding(16.dp)
    ) {
        when (envState) {
            is UiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator(color = PastelYellow)
            }
            is UiState.Error -> Text(
                text = (envState as UiState.Error).message ?: "Error",
                color = LightGreen,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            is UiState.Success -> {
                val env = (envState as UiState.Success<EnviromentItem>).data

                // --- Encabezado ---
                Text(env.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = PastelYellow)
                Spacer(Modifier.height(12.dp))

                // Imagen principal
                AsyncImage(
                    model = env.image,
                    contentDescription = env.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(Modifier.height(12.dp))

                // Descripción
                Text(env.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = PastelYellow)
                Spacer(Modifier.height(20.dp))


                when (animalsState) {
                    is UiState.Loading -> CircularProgressIndicator(color = PastelYellow)
                    is UiState.Error -> Text(
                        text = (animalsState as UiState.Error).message ?: "Error al cargar animales",
                        color = LightGreen
                    )
                    is UiState.Success -> {
                        val list = (animalsState as UiState.Success<List<AnimalsItem>>).data
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth(),  // ocupa todo el ancho
                            horizontalArrangement = Arrangement.spacedBy(
                                12.dp,
                                Alignment.CenterHorizontally   // ahora todos se centran
                            )
                        ) {
                            items(list) { animal ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        model = animal.image,
                                        contentDescription = animal.name,
                                        modifier = Modifier
                                            .size(120.dp)      // tamaño aumentado
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(Modifier.height(8.dp))    // espaciado ajustado
                                    Text(
                                        animal.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = PastelYellow
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

