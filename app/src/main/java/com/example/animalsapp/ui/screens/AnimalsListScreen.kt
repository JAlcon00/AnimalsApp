package com.example.animalsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.ui.navigation.Screen
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.AnimalsListViewModel

@Composable
fun AnimalsListScreen(
    navController: NavHostController,
    vm: AnimalsListViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Error -> {
            val message = (uiState as UiState.Error).message
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Error: $message")
                Spacer(Modifier.height(8.dp))
                Button(onClick = { vm.fetchAnimals() }) {
                    Text("Reintentar")
                }
            }
        }
        is UiState.Success -> {
            val list = (uiState as UiState.Success<List<AnimalsItem>>).data
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(list) { animal ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AnimalDetail.createRoute(animal.id)
                                )
                            }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = animal.image,
                            contentDescription = animal.name,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(animal.name)
                    }
                }
            }
        }
    }
}
