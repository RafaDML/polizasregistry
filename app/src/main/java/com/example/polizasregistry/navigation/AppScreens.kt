package com.example.polizasregistry.navigation

sealed class AppScreens (val route: String, var icon: Int?, var title: String, var limpiar: Boolean = true, var datos: Any? = null){
    object PolizaScreen: AppScreens("poliza_screen", null,  "Poliza Screen")

}
