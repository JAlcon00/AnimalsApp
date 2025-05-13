package com.example.animalsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
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
import com.example.animalsapp.ui.theme.Dimens
import com.example.animalsapp.ui.theme.PastelYellow
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.AnimalsListViewModel
import androidx.compose.foundation.layout.statusBarsPadding

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
            .statusBarsPadding()
            .padding(Dimens.spaceMedium)
    ) {
        // Header con título, descripción y botón Agregar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimens.spaceMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Animales",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp),
                    color = Color.White
                )
                Spacer(Modifier.height(Dimens.spaceSmall))
                Text(
                    text = "Conoce a los animales más increíbles del mundo",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            Button(
                onClick = { /* TODO: Acción Agregar */ },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = PastelYellow),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Pets,
                    contentDescription = "Agregar",
                    tint = DarkGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(Dimens.spaceSmall))
                Text(
                    text = "Agregar",
                    color = DarkGreen,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Cuerpo según estado
        when (uiState) {
            is UiState.Loading -> Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PastelYellow)
            }
            is UiState.Error -> Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (uiState as UiState.Error).message.orEmpty(),
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = Dimens.spaceMedium)
                )
            }
            is UiState.Success -> {
                val list = (uiState as UiState.Success<List<AnimalsItem>>).data
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
                ) {
                    items(list) { animal ->
                        Column(
                            modifier = Modifier
                                .clickable { navController.navigate("animal_detail/${animal.id}") }
                                .padding(vertical = Dimens.spaceSmall),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = animal.image,
                                contentDescription = animal.name,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(Modifier.height(Dimens.spaceSmall))
                            Text(
                                text = animal.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}