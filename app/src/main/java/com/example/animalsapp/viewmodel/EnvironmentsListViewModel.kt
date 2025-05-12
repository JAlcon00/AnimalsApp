package com.example.animalsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.repository.EnvironmentRepository
import com.example.animalsapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la lista de ambientes.
 */
class EnvironmentsListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<EnviromentItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<EnviromentItem>>> = _uiState.asStateFlow()

    init {
        fetchEnvironments()
    }

    fun fetchEnvironments() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val list = EnvironmentRepository.getEnvironments()
                UiState.Success(list)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error al cargar ambientes")
            }
        }
    }
}