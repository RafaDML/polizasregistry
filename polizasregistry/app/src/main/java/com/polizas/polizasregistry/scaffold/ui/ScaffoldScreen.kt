package com.polizas.polizasregistry.scaffold.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.components.modals.dialogoptions.ui.DialogOptionsScreen
import com.polizas.polizasregistry.components.modals.inicio.ui.InicioScreen
import com.polizas.polizasregistry.components.modals.loading.ui.LoadingScreen
import com.polizas.polizasregistry.navigation.AppScreens
import com.polizas.polizasregistry.polizas.ui.PolizaScreen
import com.polizas.polizasregistry.polizas.viewmodel.PolizaViewModel
import com.polizas.polizasregistry.scaffold.viewmodel.ScaffoldViewModel

@Composable
fun ScaffoldScreen(
    navigate: (AppScreens) -> Unit,
    scaffoldViewModel: ScaffoldViewModel = hiltViewModel(),
    polizaViewModel: PolizaViewModel = hiltViewModel()
) { 
    scaffoldViewModel.obtenerPolizas(navigate)

    showOptionsDialog(scaffoldViewModel,polizaViewModel)
    ShowLoadingScreen(scaffoldViewModel, "Cargando")

    BottomBar(navigate = navigate, scaffoldViewModel = scaffoldViewModel)
}

@Composable
fun showOptionsDialog(scaffoldViewModel: ScaffoldViewModel,polizaViewModel:PolizaViewModel) {
    val dialogOption by scaffoldViewModel.dialogInfoOptions.observeAsState(
        DialogItem(
            false,
            "Desea continuar?",
            Icons.Filled.QuestionMark
        )
    )
    val idDelete by scaffoldViewModel.idDelete.observeAsState(0)
    if (dialogOption.show) {
        DialogOptionsScreen(
            msg = dialogOption!!.msg,
            icon = dialogOption!!.icon,
            onClickAcceptButton = {polizaViewModel.eliminarPoliza(idDelete)},
            onClickCancelButton = {scaffoldViewModel.closeModal()})
    }
}

@Composable
fun ShowLoadingScreen(scaffoldViewModel: ScaffoldViewModel, msg: String) {
    val isLoading: Boolean by scaffoldViewModel.isLoading.observeAsState(initial = false)
    val msg: String by scaffoldViewModel.msg.observeAsState("")
    val message = if (msg.isNotEmpty()) msg else "Cargando"
    if (isLoading) {
        LoadingScreen(msg = message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navigate: (AppScreens) -> Unit, scaffoldViewModel: ScaffoldViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(navigate, scaffoldViewModel, navController) },

        bottomBar = { BottomNavigationBar(navController, scaffoldViewModel, navigate) },

        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    navController = navController,
                    navigate,
                    scaffoldViewModel,
                )
            }
        })
}

@Composable
fun TopBar(
    navigate: (AppScreens) -> Unit,
    scaffoldViewModel: ScaffoldViewModel,
    navController: NavHostController
) {
    TopAppBar(
        modifier = Modifier
            .height(50.dp)
            .background(Color.Black),
        backgroundColor = Color(0XFF02177f), contentColor = Color(0XFFBDBDBD),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Polizas",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 21.sp,
                        lineHeight = 27.sp
                    )
                )
            }
        },
        actions = {
            IconButton(onClick = {
                scaffoldViewModel.logout(navigate)
            }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Filled.Logout,
                    contentDescription = null,
                    tint = Color(0XFFBDBDBD),
                )
            }
        }
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    scaffoldViewModel: ScaffoldViewModel,
    navigate: (AppScreens) -> Unit
) {
    val items = listOf(
        AppScreens.PolizaScreen,
        AppScreens.InicioScreen
    )
    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = Color.White,
        contentColor = Color.White,
        contentPadding = PaddingValues(0.dp),
        elevation = 15.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { i, item ->
            val color = if (currentRoute == item.route) Color(0XFF02177f) else Color(0XFFBDBDBD)
            if (i == items.count() / 2) {
                Spacer(
                    Modifier
                        .weight(1f)
                        .padding(0.dp)
                )
            }
            BottomNavigationItem(
                modifier = Modifier.padding(0.dp),
                icon = {
                    BottonNavigationItemIcon(item, color)
                },
                label = {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 9.sp,
                        color = color
                    )
                },
                enabled = item.title != "Historial",
                selectedContentColor = Color(0XFF02177f),
                unselectedContentColor = Color(0XFFBDBDBD),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        if ("polizascreen" == item.route) scaffoldViewModel.obtenerPolizas(navigate)
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = false
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun BottonNavigationItemIcon(item: AppScreens, color: Color) {

    Icon(
        imageVector = ImageVector.vectorResource(id = item.icon!!),
        contentDescription = item.title,
        tint = color
    )

}

@Composable
fun Navigation(
    navController: NavHostController,
    navigate: (AppScreens) -> Unit,
    scaffoldViewModel: ScaffoldViewModel,
) {
    NavHost(navController, startDestination = AppScreens.PolizaScreen.route) {
        composable(AppScreens.PolizaScreen.route) {
            scaffoldViewModel.changeTitulo(AppScreens.PolizaScreen)
            PolizaScreen(scaffoldViewModel = scaffoldViewModel, navController = navController)
        }
        composable(AppScreens.InicioScreen.route) {
            scaffoldViewModel.changeTitulo(AppScreens.InicioScreen)
            InicioScreen(scaffoldViewModel = scaffoldViewModel, navController = navController)
        }
    }


}