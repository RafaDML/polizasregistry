package com.polizas.polizasregistry.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

class MyNavActions(
    navHostController: NavHostController,
) {
    val navigateTo = { navBackStackEntry: NavBackStackEntry, route: String, limpiar: Boolean ->
        if (navBackStackEntry.lifecycleIsResumed()) {
            navHostController.navigate(route) {
                if (limpiar) {
                    popUpTo(0)
                }
            }
        }
    }


}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.getLifecycle().currentState == Lifecycle.State.RESUMED