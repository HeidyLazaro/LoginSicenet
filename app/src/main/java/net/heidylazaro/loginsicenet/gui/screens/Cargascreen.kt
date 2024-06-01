package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.AppViewModel
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.CargaViewModel
import net.heidylazaro.loginsicenet.model.Carga

@Composable
fun HorarioContent(horario: List<Carga>) {
    val diasSemana = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")

    Column {
        // Encabezado de la tabla
        Row {
            diasSemana.forEach { dia ->
                Text(
                    text = dia,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        // Contenido de la tabla
        LazyColumn {
            items(1) { hora ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    diasSemana.forEach { dia ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val materiasEnHora = horario.filter { materia ->
                                when (dia) {
                                    "Lunes" -> materia.lunes != ""
                                    "Martes" -> materia.martes != ""
                                    "Miércoles" -> materia.miercoles != ""
                                    "Jueves" -> materia.jueves != ""
                                    "Viernes" -> materia.viernes != ""
                                    else -> false
                                }
                            }

                            Column {
                                materiasEnHora.forEach { materia ->
                                    Text(text = materia.materia, style = MaterialTheme.typography.bodyMedium)
                                    val horarioDia = when (dia) {
                                        "Lunes" -> materia.lunes
                                        "Martes" -> materia.martes
                                        "Miércoles" -> materia.miercoles
                                        "Jueves" -> materia.jueves
                                        "Viernes" -> materia.viernes
                                        else -> "" // Valor por defecto si el día no coincide
                                    }
                                    if (horarioDia != "") {
                                        val horaClase = horarioDia.split(" ").first() // Obtiene "HH:mm"
                                        val aula = horarioDia.substringAfter("Aula: ") // Obtiene "nombreAula"

                                        Text(text = "$horaClase", style = MaterialTheme.typography.bodySmall)
                                        Text(text = "$aula", style = MaterialTheme.typography.bodySmall)
                                    }
                                    Divider(modifier = Modifier.padding(top = 4.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargaAcademicaScreen(navController: NavHostController, appContainer: DefaultContainer) {
    val viewModel: CargaViewModel = viewModel(factory = AppViewModel(appContainer))
    val horario by viewModel.horario.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Horario") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
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
            when {
                isLoading -> CircularProgressIndicator()
                error != null -> Text("Error: $error", color = Color.Red)
                else -> HorarioContent(horario) // Pasa el horario al composable
            }
        }
    }
}