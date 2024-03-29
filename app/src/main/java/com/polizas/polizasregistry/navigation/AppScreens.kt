package com.polizas.polizasregistry.navigation

import com.example.polizasregistry.R

sealed class AppScreens (val route: String, var icon: Int?, var title: String, var limpiar: Boolean = true, var datos: Any? = null){
    object PolizaScreen: AppScreens("polizascreen", R.drawable.icono_card,  "Polizas")
    object InventarioScreen: AppScreens("inventarioscreen", R.drawable.inventory,  "Inventario")
    object EmpleadosScreen: AppScreens("empleadosscreen", R.drawable.employees_icon,  "Empleados")
    object LoginScreen: AppScreens("login_screen", R.drawable.icono_entrega_domicilio,  "Login Screen")

    object ScaffoldScreen: AppScreens("home_scaffold_screen", R.drawable.icono_card,  "Home")
    //SCREEN FOR SCAFFOLD
    object InicioScreen: AppScreens("inicio", R.drawable.home_icon,  "Inicio")

    object CreatePolizaScreen: AppScreens("create_poliza", R.drawable.icono_card, "Nueva Póliza")



}
