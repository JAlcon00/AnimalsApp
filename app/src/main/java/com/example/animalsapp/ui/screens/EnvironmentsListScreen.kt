package com.example.animalsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
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
import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.ui.navigation.Screen
import com.example.animalsapp.ui.theme.DarkGreen
import com.example.animalsapp.ui.theme.Dimens
import com.example.animalsapp.ui.theme.LightGreen
import com.example.animalsapp.utils.UiState
import com.example.animalsapp.viewmodel.EnvironmentsListViewModel

@Composable
fun EnvironmentsListScreen(
    navController: NavHostController,
    vm: EnvironmentsListViewModel = viewModel()
) {
    val state by vm.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.spaceMedium)
            .background(DarkGreen),
        contentPadding = PaddingValues(Dimens.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceLarge)
    ) {
        when (state) {
            is UiState.Loading -> item { /* indicador de carga si quieres */ }
            is UiState.Error   -> item { /* texto de error */ }
            is UiState.Success -> {
                val list = (state as UiState.Success<List<EnviromentItem>>).data
                items(list) { env ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screen.EnvDetail.createRoute(env.id))
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = env.image,
                            contentDescription = env.name,
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                                
                        )
                        Spacer(Modifier.height(Dimens.spaceSmall))
                        Text(text = env.name, color = Color.White)
                    }
                }
            }
        }
    }
}
