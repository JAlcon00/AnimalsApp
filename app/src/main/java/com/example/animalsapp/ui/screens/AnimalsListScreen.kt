package com.example.animalsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            .verticalScroll(rememberScrollState())
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
                shape = RoundedCornerShape(Dimens.spaceLarge)
            ) {
                Icon(Icons.Filled.Pets, contentDescription = null)
                Spacer(modifier = Modifier.width(Dimens.spaceSmall))
                Text(text = "Agregar")
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spaceMedium))

        when (uiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PastelYellow)
                }
            }
            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: $message", color = Color.Red)
                }
            }
            is UiState.Success -> {
                val list = (uiState as UiState.Success<List<AnimalsItem>>).data
                LazyColumn(
                    contentPadding = PaddingValues(Dimens.spaceMedium),
                    verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
                ) {
                    items(list) { animal ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Screen.AnimalDetail.createRoute(animal.id))
                                }
                                .padding(vertical = Dimens.spaceMedium),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = animal.image,
                                contentDescription = animal.name,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, LightGreen, CircleShape)
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

        Spacer(modifier = Modifier.height(80.dp)) // Espacio para el bottom bar
    }
}


