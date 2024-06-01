package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import net.heidylazaro.loginsicenet.data.DefaultContainer
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.AppViewModel
import net.heidylazaro.loginsicenet.gui.screens.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, appContainer: DefaultContainer) {
    val loginViewModel: LoginViewModel = viewModel(factory = AppViewModel(appContainer))

    var controlNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Recopilar el estado de isLoading y error para mostrarlo en la UI
    val isLoading by loginViewModel.isLoading.collectAsState()
    val error by loginViewModel.error.collectAsState()

    // Lanzar la corrutina cuando sea necesario
    LaunchedEffect(loginViewModel.loginSuccessful) {
        loginViewModel.loginSuccessful.collect { isSuccessful ->
            if (isSuccessful) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
    }

    Box(

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = controlNumber,
                onValueChange = { controlNumber = it },
                label = { Text("Usuario") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            }

            error?.let { errorMessage ->
                Text(errorMessage, color = Color.Red)
            }

            Button(onClick = { loginViewModel.login(controlNumber, password) }) {
                Text("Iniciar sesión")
            }
        }
    }
}
