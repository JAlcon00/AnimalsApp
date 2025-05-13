package com.example.animalsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.repository.AnimalsRepository
import com.example.animalsapp.repository.EnvironmentRepository
import com.example.animalsapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para detalle de ambiente, con lista de animales filtrados.
 */
class EnvDetailViewModel : ViewModel() {

    // Estado del propio ambiente
    private val _uiState = MutableStateFlow<UiState<EnviromentItem>>(UiState.Loading)
    val uiState: StateFlow<UiState<EnviromentItem>> = _uiState.asStateFlow()

    // Estado de los animales en este ambiente
    private val _animalsState = MutableStateFlow<UiState<List<AnimalsItem>>>(UiState.Loading)
    val animalsState: StateFlow<UiState<List<AnimalsItem>>> = _animalsState.asStateFlow()

    /** Carga detalle del ambiente */
    fun fetchEnvironment(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val env = EnvironmentRepository.getEnvironmentById(id)
                UiState.Success(env)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error al cargar detalle de ambiente")
            }
        }
    }

    /** Carga y filtra animales de acuerdo al environmentId */
    fun fetchAnimals(envId: String) {
        viewModelScope.launch {
            _animalsState.value = UiState.Loading
            _animalsState.value = try {
                val all = AnimalsRepository.getAnimals()
                // Filtramos solo los que viven en este ambiente
                val filtered = all.filter { it.environmentId == envId }
                UiState.Success(filtered)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error al cargar animales")
            }
        }
    }
}
