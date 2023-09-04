@file:Suppress("UNCHECKED_CAST")

package com.example.polizasregistry.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.polizasregistry.Polizas.ui.PolizaScreen




@Composable
fun MyNavGraph() {
    val navHostController = rememberNavController()
    val myNavActions = remember(navHostController) {
        MyNavActions(navHostController)
    }

    NavHost(navController = navHostController, startDestination = AppScreens.PolizaScreen.route) {
        composable(AppScreens.PolizaScreen.route) { navBackStackEntry ->
            PolizaScreen(navigate = { ruta ->
                myNavActions.navigateTo(
                    navBackStackEntry,
                    ruta.route,
                    ruta.limpiar
                )
            })
        }
    }
}
