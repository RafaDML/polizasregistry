package com.polizas.polizasregistry.components.modals.inicio.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.polizas.polizasregistry.scaffold.viewmodel.ScaffoldViewModel

@Composable
fun InicioScreen(
    scaffoldViewModel: ScaffoldViewModel,
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "PANTALLA INICIAL",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )

    }
}