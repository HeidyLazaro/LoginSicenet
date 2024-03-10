package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.heidylazaro.loginsicenet.network.SicenetApi
import java.io.IOException

sealed interface LoginSicenetUiState {
    data class Success(val accesoAlumno: String) : LoginSicenetUiState
    object Error : LoginSicenetUiState
    object Loading : LoginSicenetUiState
}

class LoginViewModel: ViewModel(){
    var loginUiState: LoginSicenetUiState by mutableStateOf(LoginSicenetUiState.Loading)
        private set

    init {
        getLoginSicenet()
    }

    fun getLoginSicenet() {
        //loginUiState = "Set the Sicenet status response here!"

        viewModelScope.launch {
            loginUiState = try {
                val response = SicenetApi.retrofitService.obtenerDatosXML()
                val xmlString = response
                LoginSicenetUiState.Success(
                    xmlString.string()
                    //"Success: ${xmlString.size} Mars photos retrieved"
                )
            }catch(e: IOException){
                LoginSicenetUiState.Error
            }

        }


    }
}