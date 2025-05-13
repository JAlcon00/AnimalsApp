package com.example.animalsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.ui.navigation.Screen
import com.example.animalsapp.ui.theme.DarkGreen
import com.example.animalsapp.ui.theme.Dimens
import com.example.animalsapp.ui.theme.LightGreen
import com.example.animalsapp.ui.theme.PastelYellow
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.AnimalsListViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets

@Composable
fun AnimalsListScreen(
    navController: NavHostController,
    vm: AnimalsListViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreen)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spaceMedium, vertical = Dimens.spaceLarge),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Animales",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(Dimens.spaceSmall))
                Text(
                    text = "Conoce a los animales más increíbles del mundo",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            Button(
                onClick = { /* TODO: acción Agregar */ },
                colors = ButtonDefaults.buttonColors(containerColor = PastelYellow),
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Pets, contentDescription = "Agregar")
                Spacer(modifier = Modifier.width(Dimens.spaceSmall))
                Text(text = "Agregar")
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceMedium))

        // Lista
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(Dimens.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
        ) {
            when (uiState) {
                is UiState.Loading -> item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PastelYellow)
                    }
                }
                is UiState.Error -> item {
                    Text(
                        text = (uiState as UiState.Error).message ?: "Error desconocido",
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                is UiState.Success -> {
                    val list = (uiState as UiState.Success<List<AnimalsItem>>).data
                    items(list) { animal ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Screen.AnimalDetail.createRoute(animal.id))
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = animal.image,
                                contentDescription = animal.name,
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)

                            )
                            Spacer(modifier = Modifier.height(Dimens.spaceSmall))
                            Text(
                                text = animal.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
