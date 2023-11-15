package com.polizas.polizasregistry.polizas.ui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.polizasregistry.R
import com.polizas.polizasregistry.scaffold.viewmodel.ScaffoldViewModel
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.model.Polizas

@Composable
fun PolizaScreen(
    scaffoldViewModel: ScaffoldViewModel,
    navController: NavHostController

) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()

    ) {
        PolizaBody(
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFdae2f7)), scaffoldViewModel
        )

    }
}

@Composable
fun PolizaBody(modifier: Modifier, scaffoldViewModel: ScaffoldViewModel) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        ListaPolizas(scaffoldViewModel)
    }

}

@Composable
fun ListaPolizas(scaffoldViewModel: ScaffoldViewModel) {
    val datos: ObtenerPolizasItem by scaffoldViewModel.polizasItem.observeAsState(
        ObtenerPolizasItem()
    )
    Log.i("POLIZAS VIEWMODEL", " VALOR DE DATOS  ${datos.polizas.size}")

    LazyColumn() {
        items(datos.polizas) { item ->
            Log.i("LAZY COLUMN", item.toString())
            CardItemPoliza(item)
        }
    }
}


@Composable
fun CardItemPoliza(datos: Polizas) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 18.dp)
            .height(35.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                .background(Color(0XFF02177f)), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Poliza No. ${datos.idPoliza}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    lineHeight = 27.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd){
                IconButton(onClick = {
                }) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color(0XFFBDBDBD),
                    )
                }
            }


        }

    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Cyan)

    ) {
        Column(

        ) {
            Box(
                Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = datos.inventario?.nombre.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        lineHeight = 27.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
            }
            CardContentPoliza(
                stringResource(id = R.string.PolizaCardContentCantidad),
                datos.cantidad.toString()
            )
            CardContentPoliza(
                stringResource(id = R.string.PolizaCardContentEmpleado),
                " ${datos.empleado?.nomEmpleado.toString()} ${datos.empleado?.apEmpleado.toString()} "
            )
            CardContentPoliza(
                stringResource(id = R.string.PolizaCardContentFecha),
                datos.fecha.toString()
            )

        }
    }
}

@Composable
fun CardContentPoliza(description: String, valueDesc: String) {
    var fontWeight = FontWeight.Normal
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
        }
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Box(modifier = modifier)

}