package com.polizas.polizasregistry

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.navigation.AppScreens
import com.polizas.polizasregistry.navigation.MyNavGraph
import com.polizas.polizasregistry.ui.theme.PolizasRegistryTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatDelegate


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContent {

            PolizasRegistryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val token =
                        SessionTokenManager.getToken(PolizaApplication.getApplicationContext()!!)

                    if (!token.isNullOrEmpty()) MyAppView(AppScreens.ScaffoldScreen) else MyAppView(AppScreens.LoginScreen)

                }
            }
        }
    }
}

@Composable
fun MyAppView(
    screen: AppScreens
) {
    Log.i("RAFA", "PRIMER METODO")
    MyNavGraph(
        screen
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello android $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PolizasRegistryTheme {
        Greeting("Android")
    }
}