package net.heidylazaro.loginsicenet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.heidylazaro.loginsicenet.data.DefaultContainer
//import net.heidylazaro.loginsicenet.gui.LoginSicenetApp
import net.heidylazaro.loginsicenet.gui.screens.AppNavigation
import net.heidylazaro.loginsicenet.gui.theme.LoginSicenetTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSicenetTheme {
                val appContainer = DefaultContainer(applicationContext = applicationContext)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //LoginSicenetApp()
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        AppNavigation(innerPadding, appContainer)
                    }
                }
            }
        }
    }
}