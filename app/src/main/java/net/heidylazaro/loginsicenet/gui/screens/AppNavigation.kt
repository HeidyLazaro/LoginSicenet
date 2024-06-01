package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.heidylazaro.loginsicenet.data.DefaultContainer

@Composable
fun AppNavigation(innerPadding: PaddingValues, appContainer: DefaultContainer) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, appContainer) }
        composable("home") { HomeScreen(navController, appContainer) }
        composable("cargaacademica") { CargaAcademicaScreen(navController, appContainer) }
        composable("unidades") { CalificacionesUnidadesScreen(navController, appContainer) }
        composable("finales") { CalificacionesFinalesScreen(navController, appContainer) }
        composable("kardex") { KardexScreen(navController, appContainer)}
    }
}