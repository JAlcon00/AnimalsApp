package com.example.animalsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.EnvDetailViewModel

@Composable
fun EnvDetailScreen(
    id: String,
    navController: NavHostController,
    vm: EnvDetailViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(id) {
        vm.fetchEnvironment(id)
    }

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
                Button(onClick = { vm.fetchEnvironment(id) }) {
                    Text("Reintentar")
                }
            }
        }
        is UiState.Success<*> -> {
            val env = (uiState as UiState.Success<EnviromentItem>).data
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(text = env.name)
                Spacer(Modifier.height(12.dp))
                AsyncImage(
                    model = env.image,
                    contentDescription = env.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(text = env.description)
            }
        }
    }
}