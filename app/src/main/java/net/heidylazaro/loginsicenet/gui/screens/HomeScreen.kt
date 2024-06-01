package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.heidylazaro.loginsicenet.R
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.AppViewModel
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.HomeViewModel
//import net.heidylazaro.loginsicenet.gui.screens.viewmodel.LoginSicenetUiState
import net.heidylazaro.loginsicenet.gui.theme.LoginSicenetTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController,
               appContainer: DefaultContainer) {
    val viewModel: HomeViewModel = viewModel(factory = AppViewModel(appContainer))

    val datosAlumno by viewModel.datosAlumno.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Información del Alumno") }
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
            // Manejo de estados de carga y error
            when {
                isLoading -> CircularProgressIndicator()
                error != null -> Text("Error: $error", color = Color.Red)
                datosAlumno != null -> {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Nombre: ${datosAlumno?.nombre}", style = MaterialTheme.typography.headlineSmall)
                            Text(text = "Matrícula: ${datosAlumno?.matricula}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Carrera: ${datosAlumno?.carrera}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Especialidad: ${datosAlumno?.especialidad}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Semestre Actual: ${datosAlumno?.semActual}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
            Button(onClick = { navController.navigate("cargaacademica") }) {
                Text("Ver Carga Academica")
            }
            Button(onClick = { navController.navigate("unidades") }) {
                Text("Ver Calificaciones por Unidad")
            }
            Button(onClick = { navController.navigate("finales") }) {
                Text("Ver Calificaciones Finales")
            }
            Button(onClick = { navController.navigate("kardex") }) {
                Text("Ver Kardex")
            }
        }
    }
}

@Composable
fun ResultScreen(login: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = login)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    LoginSicenetTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
