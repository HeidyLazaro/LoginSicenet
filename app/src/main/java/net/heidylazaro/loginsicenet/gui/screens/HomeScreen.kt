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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.heidylazaro.loginsicenet.R
import net.heidylazaro.loginsicenet.gui.theme.LoginSicenetTheme

@Composable
fun HomeScreen(
    loginUiState: LoginSicenetUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    //ResultScreen(loginUiState, modifier.padding(top = contentPadding.calculateTopPadding()))
    when (loginUiState) {
        is LoginSicenetUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is LoginSicenetUiState.Success -> ResultScreen(
            loginUiState.accesoAlumno, modifier = modifier.fillMaxWidth()
        )

        is LoginSicenetUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
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
