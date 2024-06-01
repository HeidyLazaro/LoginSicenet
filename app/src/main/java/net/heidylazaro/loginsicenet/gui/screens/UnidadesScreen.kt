package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.AppViewModel
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.UnidadesViewModel
import net.heidylazaro.loginsicenet.model.CalificacionesSicenet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalificacionesUnidadesContent(calificaciones: List<CalificacionesSicenet>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(calificaciones.size) { index ->
            val calificacion = calificaciones[index]
            Card(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Materia: ${calificacion.materia}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text("Grupo: ${calificacion.grupo}")
                    val calificaciones = mapOf(
                        "U1" to calificacion.c1,
                        "U2" to calificacion.c2,
                        "U3" to calificacion.c3,
                        "U4" to calificacion.c4,
                        "U5" to calificacion.c5,
                        "U6" to calificacion.c6,
                        "U7" to calificacion.c7,
                        "U8" to calificacion.c8,
                        "U9" to calificacion.c9,
                        "U10" to calificacion.c10,
                        "U11" to calificacion.c11,
                        "U12" to calificacion.c12,
                        "U13" to calificacion.c13
                    ).filterValues { it != null }

                    calificaciones.forEach { (unidad, calificacion) ->
                        Text("$unidad: $calificacion")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalificacionesUnidadesScreen(
    navController: NavHostController,
    appContainer: DefaultContainer
) {
    val viewModel: UnidadesViewModel = viewModel(factory = AppViewModel(appContainer))
    val calificaciones by viewModel.calificacionesUnidades.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calificaciones") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) { // Botón de retroceso
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Manejo de estados de carga y error (igual que en HorarioScreen)
            when {
                isLoading -> CircularProgressIndicator()
                error != null -> Text("Error: $error", color = Color.Red)
                else -> CalificacionesUnidadesContent(calificaciones)
            }
        }
    }
}

