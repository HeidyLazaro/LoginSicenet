package net.heidylazaro.loginsicenet.gui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.heidylazaro.loginsicenet.data.DefaultContainer

class AppViewModel(private val appContainer: DefaultContainer) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(appContainer)
            HomeViewModel::class.java -> HomeViewModel(appContainer)
            CargaViewModel::class.java -> CargaViewModel(appContainer)
            UnidadesViewModel::class.java -> UnidadesViewModel(appContainer)
            FinalesViewModel::class.java -> FinalesViewModel(appContainer)
            KardexViewModel::class.java -> KardexViewModel(appContainer)
            // Agrega aquí otros ViewModels que utilicen AppContainer
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}