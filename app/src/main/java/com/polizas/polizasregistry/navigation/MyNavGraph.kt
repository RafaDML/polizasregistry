@file:Suppress("UNCHECKED_CAST")

package com.polizas.polizasregistry.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polizas.polizasregistry.login.ui.LoginScreen
import com.polizas.polizasregistry.scaffold.ui.ScaffoldScreen


@Composable
fun MyNavGraph(screen: AppScreens) {
    val navHostController = rememberNavController()
    val myNavActions = remember(navHostController) {
        MyNavActions(navHostController)
    }

    NavHost(navController = navHostController, startDestination = screen.route) {

        composable(AppScreens.LoginScreen.route) { navBackStackEntry ->
            LoginScreen(navigate = { ruta ->
                myNavActions.navigateTo(
                    navBackStackEntry,
                    ruta.route,
                    ruta.limpiar
                )
            })
        }
        composable(AppScreens.ScaffoldScreen.route) { navBackStackEntry ->
            ScaffoldScreen( navigate = { ruta ->
                myNavActions.navigateTo(
                    navBackStackEntry,
                    ruta.route,
                    ruta.limpiar
                )
            })
        }
    }
}
