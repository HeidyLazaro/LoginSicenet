package net.heidylazaro.loginsicenet.gui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.model.CalificacionesSicenet

class UnidadesViewModel(private val appContainer: DefaultContainer) : ViewModel() {
    private val calificaciones = MutableStateFlow<List<CalificacionesSicenet>>(emptyList())
    val calificacionesUnidades: StateFlow<List<CalificacionesSicenet>> = calificaciones

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val calificacionesJson = appContainer.sicenetRepo.getCalificacionesUnidades()
                calificacionesJson?.let { json ->
                    calificaciones.value = Json.decodeFromString<List<CalificacionesSicenet>>(json)
                } ?: run {
                    _error.value = "Error: No se pudieron obtener las calificaciones"
                }
            } catch (e: Exception) {
                _error.value = "Error al cargar las calificaciones: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

