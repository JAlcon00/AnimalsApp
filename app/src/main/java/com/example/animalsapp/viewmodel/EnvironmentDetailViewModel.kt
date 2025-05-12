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
 * ViewModel para detalle de ambiente.
 */
class EnvDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<EnviromentItem>>(UiState.Loading)
    val uiState: StateFlow<UiState<EnviromentItem>> = _uiState.asStateFlow()

    fun fetchEnvironment(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val item = EnvironmentRepository.getEnvironmentById(id)
                UiState.Success(item)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error al cargar detalle de ambiente")
            }
        }
    }
}
