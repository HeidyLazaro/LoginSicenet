package net.heidylazaro.loginsicenet.gui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.heidylazaro.loginsicenet.R
import net.heidylazaro.loginsicenet.gui.theme.LoginSicenetTheme

@Composable
fun HomeScreen(
    loginUiState: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
    ResultScreen(loginUiState, modifier.padding(top = contentPadding.calculateTopPadding()))
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

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    LoginSicenetTheme {
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
