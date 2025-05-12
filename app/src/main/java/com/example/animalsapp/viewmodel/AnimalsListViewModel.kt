package com.example.animalsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.repository.AnimalsRepository
import com.example.animalsapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la lista de animales.
 * Expone estados de carga, éxito y error mediante UiState.
 */
class AnimalsListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<AnimalsItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<AnimalsItem>>> = _uiState.asStateFlow()

    init {
        fetchAnimals()
    }

    /**
     * Lanza la carga de animales y actualiza el estado.
     */
    fun fetchAnimals() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val list = AnimalsRepository.getAnimals()
                UiState.Success(list)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error desconocido al cargar animales")
            }
        }
    }
}
