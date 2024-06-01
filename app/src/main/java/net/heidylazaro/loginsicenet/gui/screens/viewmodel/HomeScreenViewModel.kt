package net.heidylazaro.loginsicenet.gui.screens.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.model.DatosAlumno
import java.text.SimpleDateFormat

class HomeViewModel(private val appContainer: DefaultContainer) : ViewModel() {
    val gson = Gson()
    private val _datosAlumno = MutableStateFlow<DatosAlumno?>(null)
    val datosAlumno: StateFlow<DatosAlumno?> = _datosAlumno

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    init {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val datos = appContainer.sicenetRepo.getAlumno()
                val carga = appContainer.sicenetRepo.getCargaAcademica()
                val kardex = appContainer.sicenetRepo.getKardex(1)
                Log.d("carga","$carga")
                Log.d("kardex","$kardex")
                datos?.let { json ->
                    val dAlumno = gson.fromJson(json, DatosAlumno::class.java)
                    val sdf = SimpleDateFormat("dd/MM/yyyy|HH:mm")
                    val fecha = sdf.parse(dAlumno.fechaReins)
                    _datosAlumno.value = dAlumno.copy(fechaReins = fecha.toString()) // Actualizar el valor
                } ?: run {
                    _error.value = "Error al cargar datos del alumno"
                }
            } catch (e: Exception){
                Log.e("HomeViewModel","Error al cargar datos del alumno: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}