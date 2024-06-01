package net.heidylazaro.loginsicenet.gui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.model.CalificacionesFinales
import net.heidylazaro.loginsicenet.model.CalificacionesSicenet

class FinalesViewModel (private val appContainer: DefaultContainer) : ViewModel() {
    private val calificacionesFinales = MutableStateFlow<List<CalificacionesFinales>>(emptyList())
    val califFinal: StateFlow<List<CalificacionesFinales>> = calificacionesFinales

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val calificacionesFinalesJson = appContainer.sicenetRepo.getCalificacionesFinales(0)
                calificacionesFinalesJson?.let { json ->
                    calificacionesFinales.value = Json.decodeFromString<List<CalificacionesFinales>>(json)
                } ?: run {
                    _error.value = "Error: No se pudieron obtener las calificaciones finales"
                }
            } catch (e: Exception) {
                _error.value = "Error al cargar las calificaciones finales: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
