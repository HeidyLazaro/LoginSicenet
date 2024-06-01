package net.heidylazaro.loginsicenet.gui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.model.Carga
import net.heidylazaro.loginsicenet.network.CargaAcademica

class CargaViewModel(private val appContainer: DefaultContainer) : ViewModel() {
    private val _horario = MutableStateFlow<List<Carga>>(emptyList())
    val horario: StateFlow<List<Carga>> = _horario

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val horarioJson = appContainer.sicenetRepo.getCargaAcademica()
                horarioJson?.let { json ->
                    _horario.value = Json.decodeFromString<List<Carga>>(json)
                } ?: run {
                    _error.value = "Error: No se pudo obtener el horario"
                }
            } catch (e: Exception) {
                _error.value = "Error al cargar el horario: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}