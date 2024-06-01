package net.heidylazaro.loginsicenet.gui.screens.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.model.KardexResponse
import net.heidylazaro.loginsicenet.model.Promedio
import net.heidylazaro.loginsicenet.model.Kardex

class KardexViewModel(private val appContainer: DefaultContainer) : ViewModel() {
    private val _kardex = MutableStateFlow<List<Kardex>>(emptyList())
    val kardex: StateFlow<List<Kardex>> = _kardex
    private val _promedio = MutableStateFlow<Promedio?>(null)
    val promedio: StateFlow<Promedio?> = _promedio
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val kardexJson = appContainer.sicenetRepo.getKardex(0)
                kardexJson?.let { json ->
                    val kardexResponse = Json.decodeFromString<KardexResponse>(json)
                    _kardex.value = kardexResponse.result.kardex
                    _promedio.value = kardexResponse.result.Promedio
                } ?: run {
                    _error.value = "Error: No se pudo obtener el kardex"
                }
            } catch (e: Exception) {
                _error.value = "Error al cargar el kardex: ${e.message}"
                Log.e("KardexError", "${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}