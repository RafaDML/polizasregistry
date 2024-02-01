package com.polizas.polizasregistry.components.modals.inventario.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.polizasregistry.R
import com.polizas.polizasregistry.components.modals.empleados.viewmodel.EmpleadoViewModel
import com.polizas.polizasregistry.components.modals.inventario.domain.model.Inventario
import com.polizas.polizasregistry.components.modals.inventario.domain.model.ObtenerInventarioItem
import com.polizas.polizasregistry.components.modals.inventario.viewmodel.InventarioViewModel
import com.polizas.polizasregistry.components.modals.loading.ui.LoadingScreen
import com.polizas.polizasregistry.navigation.AppScreens
import com.polizas.polizasregistry.scaffold.viewmodel.ScaffoldViewModel

@Composable
fun InventarioScreen(
    inventarioViewModel: InventarioViewModel = hiltViewModel(),
    navigate: (AppScreens) -> Unit,
    navController: NavController

) {
    ShowLoadingScreen(inventarioViewModel, "Cargando")

    LaunchedEffect(Unit) {
        Log.i("RAFA", "recomposición inicial ")

        inventarioViewModel.obtenerInventario { (navigate) }

    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)

    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomEnd) {
            ListaEmpleados(inventarioViewModel, navigate)
        }

    }
}

@Composable
fun ShowLoadingScreen(inventarioViewModel: InventarioViewModel, msg: String) {
    val isLoading: Boolean by inventarioViewModel.isLoading.observeAsState(initial = false)
    val msg: String by inventarioViewModel.msg.observeAsState("")
    val message = if (msg.isNotEmpty()) msg else "Cargando"
    if (isLoading) {
        LoadingScreen(msg = message)
    }
}


@Composable
fun ListaEmpleados(inventarioViewModel: InventarioViewModel, navigate: (AppScreens) -> Unit) {
    val datos: ObtenerInventarioItem by inventarioViewModel.inventarioItem.observeAsState(
        ObtenerInventarioItem()
    )
    Log.i("POLIZAS VIEWMODEL", " VALOR DE DATOS  ${datos.inventario.size}")

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(datos.inventario) { item ->
            Log.i("LAZY COLUMN", item.toString())
            CardItemPoliza(item, inventarioViewModel, navigate)
        }
    }
}


@Composable
fun CardContentPoliza(
    description: String,
    valueDesc: String,
    inventarioViewModel: InventarioViewModel,
    datos: Inventario
) {
    var fontWeight: FontWeight
    var customPadding = 2.dp
    if (description == "Fecha") {
        fontWeight = FontWeight.SemiBold
        customPadding = 10.dp
    }
    fontWeight = if (description == "Fecha") FontWeight.Bold else FontWeight.Normal

    Box(
        Modifier.padding(start = 30.dp, bottom = customPadding),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = description,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    lineHeight = 27.sp
                ),
                color = Color.Black
            )
            Text(
                text = " : $valueDesc",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 27.sp,
                    fontWeight = fontWeight
                ),
                color = Color.Black
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                contentAlignment = Alignment.TopEnd
            ) {

            }
        }
    }
}


@Composable
fun CardItemPoliza(
    datos: Inventario,
    inventarioViewModel: InventarioViewModel,
    navigate: (AppScreens) -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(35.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(7.dp, 7.dp, 0.dp, 0.dp))
                .background(Color(0XFF02177f)), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "${datos.nombre}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    lineHeight = 27.sp,
                    fontWeight = FontWeight.Bold
                ),
            )


        }

    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(0.dp, 0.dp, 7.dp, 7.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Cyan)

    ) {
        Column(

        ) {

            CardContentPoliza(
                stringResource(id = R.string.PolizaCardContentCantidad),
                datos.cantidad.toString(),
                inventarioViewModel,
                datos
            )
            CardContentPoliza(
                stringResource(id = R.string.InventarioCardContentName),
                " ${datos.sku.toString()} ",
                inventarioViewModel,
                datos
            )
        }
    }
}
