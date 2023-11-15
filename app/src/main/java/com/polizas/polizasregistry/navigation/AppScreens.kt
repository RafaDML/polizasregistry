package com.polizas.polizasregistry.navigation

import com.example.polizasregistry.R

sealed class AppScreens (val route: String, var icon: Int?, var title: String, var limpiar: Boolean = true, var datos: Any? = null){
    object PolizaScreen: AppScreens("polizascreen", R.drawable.icono_card,  "Polizas")
    object LoginScreen: AppScreens("login_screen", R.drawable.icono_entrega_domicilio,  "Login Screen")

    object ScaffoldScreen: AppScreens("home_scaffold_screen", R.drawable.icono_card,  "Home")
    //SCREEN FOR SCAFFOLD
    object InicioScreen: AppScreens("inicio", R.drawable.icono_card,  "Inicio")



}
