package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.heidylazaro.loginsicenet.network.SicenetApi

class LoginViewModel: ViewModel(){
    var loginUiState: String by mutableStateOf("")
        private set

    init {
        getLoginSicenet()
    }

    fun getLoginSicenet() {
        //loginUiState = "Set the Sicenet status response here!"

        viewModelScope.launch { val response = SicenetApi.retrofitService.obtenerDatosXML()
            val xmlString = response.string()
        loginUiState = xmlString}


    }
}