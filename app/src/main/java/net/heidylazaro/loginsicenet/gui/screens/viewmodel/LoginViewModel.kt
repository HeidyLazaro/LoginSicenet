package net.heidylazaro.loginsicenet.gui.screens.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.heidylazaro.loginsicenet.data.DefaultContainer
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.heidylazaro.loginsicenet.model.Acceso

/*sealed interface LoginSicenetUiState {
    data class Success(val accesoAlumno: String) : LoginSicenetUiState
    object Error : LoginSicenetUiState
    object Loading : LoginSicenetUiState
}*/

class LoginViewModel(private val appContainer : DefaultContainer) : ViewModel() {
    val gson = Gson()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loginSuccessful = MutableSharedFlow<Boolean>() // Para notificar el éxito del inicio de sesión
    val loginSuccessful: SharedFlow<Boolean> = _loginSuccessful.asSharedFlow()

    fun login(controlNumber: String, password: String) {
        viewModelScope.launch {
            try {
                val accesoAlumnoJson = appContainer.sicenetRepo.getAcceso(controlNumber, password, "ALUMNO")
                accesoAlumnoJson?.let { json ->
                    val accesoAlumno = gson.fromJson(json, Acceso::class.java)
                    if (accesoAlumno.acceso) {
                        /*LoginSicenetUiState.Success(
                            accesoAlumno.string()
                            //"Success: ${xmlString.size} Mars photos retrieved"
                        )*/
                        _loginSuccessful.emit(true) // Notificar éxito
                        appContainer.matricula = accesoAlumno.matricula
                        Toast.makeText(appContainer.applicationContext, "Bienvenido ${accesoAlumno.matricula}, Estatus: ${accesoAlumno.estatus}", Toast.LENGTH_SHORT).show()
                        Log.d("LoginViewModel", "Inicio de sesión exitoso")
                    } else {
                        /*
                        LoginSicenetUiState.Error
                         */
                        _error.value = "Número de control o contraseña incorrectos"
                        Log.e("ViewModel","Número de control o contraseña incorrectos")
                    }
                } ?: run {
                    _error.value = "Número de control o contraseña incorrectos"
                    Log.e("ViewModel","Error en la solicitud")
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error al iniciar sesión: ${e.message}")
                _error.value = "Error al iniciar sesión: ${e.message}"
                Log.e("ViewModel","Error al iniciar sesión: ${e.message}")
            } finally {
                //LoginSicenetUiState.Loading
                _isLoading.value = false
            }
        }
    }
}

/*class LoginViewModel(private val appContainer: AppContainer: DefaultContainer): ViewModel(){
    var loginUiState: LoginSicenetUiState by mutableStateOf(LoginSicenetUiState.Loading)
        private set

    /*init {
        getLoginSicenet()
    }*/

    fun getLoginSicenet(matricula: String, contrasenia: String) {
        //loginUiState = "Set the Sicenet status response here!"

        /*viewModelScope.launch {
            loginUiState = try {
                val response = loginAccesoAlumno.retrofitService.obtenerDatosXML()
                val xmlString = response
                LoginSicenetUiState.Success(
                    xmlString.string()
                    //"Success: ${xmlString.size} Mars photos retrieved"
                )
            }catch(e: IOException){
                LoginSicenetUiState.Error

    }

 */
 */

