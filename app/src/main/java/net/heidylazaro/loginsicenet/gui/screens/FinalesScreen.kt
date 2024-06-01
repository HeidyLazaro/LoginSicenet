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
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.FinalesViewModel
import net.heidylazaro.loginsicenet.model.CalificacionesFinales

@Composable
fun CalificacionesFinalesContent(calificacionesFinales: List<CalificacionesFinales>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(calificacionesFinales.size) { index ->
            val calificacion = calificacionesFinales[index]
            Card(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Materia: ${calificacion.materia}", style = MaterialTheme.typography.headlineSmall)
                    Text("Grupo: ${calificacion.grupo}")
                    Text("Calificación: ${calificacion.calif}")
                    Text("Acreditado: ${calificacion.acred}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalificacionesFinalesScreen(
    navController: NavHostController,
    appContainer: DefaultContainer
) {
    val viewModel: FinalesViewModel = viewModel(factory = AppViewModel(appContainer))
    val calificacionesFinales by viewModel.califFinal.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calificaciones Finales") },
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
                else -> CalificacionesFinalesContent(calificacionesFinales)
            }
        }
    }
}
