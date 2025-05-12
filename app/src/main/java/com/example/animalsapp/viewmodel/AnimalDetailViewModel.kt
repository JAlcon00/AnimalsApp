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
 * ViewModel para detalle de animal.
 */
class AnimalDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<AnimalsItem>>(UiState.Loading)
    val uiState: StateFlow<UiState<AnimalsItem>> = _uiState.asStateFlow()

    fun fetchAnimal(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val item = AnimalsRepository.getAnimalById(id)
                UiState.Success(item)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error al cargar detalle de animal")
            }
        }
    }
}
