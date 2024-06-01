package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.KardexViewModel
import net.heidylazaro.loginsicenet.model.Promedio
import net.heidylazaro.loginsicenet.model.Kardex

@Composable
fun KardexContent(kardex: List<Kardex>, promedio: Promedio?) {
    Column {
        promedio?.let {
            Card(modifier = Modifier.padding(8.dp)) { // Tarjeta para el promedio
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Promedio General: ${String.format("%.2f", it.promedioGeneral)}", style = MaterialTheme.typography.headlineSmall)
                    Text("Créditos Acumulados: ${it.creditosAcumulados}")
                    Text("Créditos del Plan: ${it.creditosPlan}")
                    Text("Materias Cursadas: ${it.materiasCursadas}")
                    Text("Materias Aprobadas: ${it.materiasAprobadas}")
                    Text("Avance en Créditos: ${String.format("%.2f", it.avanceCreditos)}%")
                }
            }
        }

        LazyColumn {
            items(kardex) { materia ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Materia: ${materia.materia}", style = MaterialTheme.typography.headlineSmall)
                        Text("Clave: ${materia.clvMat}")
                        Text("Créditos: ${materia.cdts}")
                        Text("Calificación: ${materia.calif}")
                        Text("Acreditado: ${materia.acred}")
                        // Mostrar periodos (si no son nulos)
                        materia.s1?.let { Text("Semestre 1: $it (${materia.p1} ${materia.a1})") }
                        materia.s2?.let { Text("Semestre 2: $it (${materia.p2} ${materia.a2})") }
                        materia.s3?.let { Text("Semestre 3: $it (${materia.p3} ${materia.a3})") }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KardexScreen(
    navController: NavHostController,
    appContainer: DefaultContainer
) {
    val viewModel: KardexViewModel = viewModel(factory = AppViewModel(appContainer))
    val kardex by viewModel.kardex.collectAsState()
    val promedio by viewModel.promedio.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kardex") },
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
            when {
                isLoading -> CircularProgressIndicator()
                error != null -> Text("Error: $error", color = Color.Red)
                else -> KardexContent(kardex, promedio) // Pasar el promedio
            }
        }
    }
}
