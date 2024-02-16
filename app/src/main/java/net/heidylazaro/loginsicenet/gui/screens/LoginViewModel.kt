package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){
    var loginUiState: String by mutableStateOf("")
        private set

    init {
        getLoginSicenet()
    }

    fun getLoginSicenet() {
        loginUiState = "Set the Sicenet status response here!"
    }
}