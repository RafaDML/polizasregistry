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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QuestionMark
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.polizasregistry.R
import com.polizas.polizasregistry.components.modals.create.models.model.PolizaMainParamsItem
import com.polizas.polizasregistry.components.modals.create.ui.CreatePolizaScreen
import com.polizas.polizasregistry.components.modals.dialog.ui.DialogScreen
import com.polizas.polizasregistry.components.modals.dialogoptions.models.DialogOptionsItem
import com.polizas.polizasregistry.components.modals.dialogoptions.ui.DialogOptionsScreen
import com.polizas.polizasregistry.components.modals.loading.ui.LoadingScreen
import com.polizas.polizasregistry.navigation.AppScreens
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.model.Polizas
import com.polizas.polizasregistry.polizas.viewmodel.PolizaViewModel

@Composable
fun PolizaScreen(
    polizaViewModel: PolizaViewModel = hiltViewModel(),
    navigate: (AppScreens) -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        Log.i("RAFA", "recomposición inicial ")
        polizaViewModel.obtenerPolizas(navigate)
    }
    ShowPolizaNewDialog(polizaViewModel, navigate)
    val showDialogOptions: DialogOptionsItem by polizaViewModel.dialogInfoOptions.observeAsState(
        DialogOptionsItem()
    )


    val showDeletePolizas: Boolean by polizaViewModel.showDeletePoliza.observeAsState(false)
    val showInfoDialgo: Boolean by polizaViewModel.showInfoDialog.observeAsState(false)
    val msg: String by polizaViewModel.msg.observeAsState("cargando")
    val isLoading: Boolean by polizaViewModel.isLoading.observeAsState(initial = false)
    if (showInfoDialgo) {
        DialogScreen(msg = msg, icon = Icons.Filled.Info) {
            polizaViewModel.closeModal()
            polizaViewModel.obtenerPolizas(navigate)
        }
    }

    if (showDeletePolizas) {
        DialogOptionsScreen(
            msg = stringResource(id = R.string.LabelDeletePoliza),
            icon = Icons.Filled.QuestionMark,
            onClickAcceptButton = {
                polizaViewModel.eliminarPoliza(navigate)
            },
            onClickCancelButton = { polizaViewModel.closeModal() })
    }
    if (isLoading) {
        LoadingScreen(msg = msg)
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()

    ) {
        PolizaBody(
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFdae2f7)), polizaViewModel, navigate, navController
        )
    }
}

@Composable
fun PolizaBody(
    modifier: Modifier,
    polizaViewModel: PolizaViewModel,
    navigate: (AppScreens) -> Unit,
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)

    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomEnd) {
            ListaPolizas(polizaViewModel, navigate)
            FloatingAddButton(
                polizaViewModel,
                navController
            )
        }
    }
}

@Composable
fun FloatingAddButton(
    polizaViewModel: PolizaViewModel,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == "polizascreen") {
        FloatingActionButton(backgroundColor = Color(0XFF02177f), onClick = {
            polizaViewModel.launchCreatePolizaDialog()
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun ListaPolizas(
    polizaViewModel: PolizaViewModel,
    navigate: (AppScreens) -> Unit
) {
    val datos: ObtenerPolizasItem by polizaViewModel.polizasItem.observeAsState(
        ObtenerPolizasItem()
    )
    Log.i("POLIZAS VIEWMODEL", " VALOR DE DATOS  ${datos.polizas.size}")

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(datos.polizas) { item ->
            Log.i("LAZY COLUMN", item.toString())
            CardItemPoliza(item, polizaViewModel, navigate)
        }
    }
}


@Composable
fun CardItemPoliza(
    datos: Polizas,
    polizaViewModel: PolizaViewModel,
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
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                IconButton(onClick = {

                    polizaViewModel.launchDeletePolizasNew(datos.idPoliza, navigate)
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
            .padding(bottom = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Cyan)

    ) {
        Column(

        ) {
            Box(
                Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
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
                datos.cantidad.toString(),
                polizaViewModel,
                datos
            )
            CardContentPoliza(
                stringResource(id = R.string.PolizaCardContentEmpleado),
                " ${datos.empleado?.nomEmpleado.toString()} ${datos.empleado?.apEmpleado.toString()} ",
                polizaViewModel,
                datos
            )
            CardContentPoliza(
                stringResource(id = R.string.PolizaCardContentFecha),
                datos.fecha.toString(),
                polizaViewModel,
                datos
            )


        }
    }
}

@Composable
fun CardContentPoliza(
    description: String,
    valueDesc: String,
    polizaViewModel: PolizaViewModel,
    datos: Polizas
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
                if (description == "Fecha") {
                    IconButton(onClick = {
                        polizaViewModel.launchUpdatePolizaDialog(
                            datos.idPoliza,
                            datos.inventario?.sku.toString(),
                            datos.cantidad.toString(),
                            datos.empleado?.idEmpleado.toString(),
                            datos.fecha.toString()
                        )
                    }) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color.Black,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShowPolizaNewDialog(polizaViewModel: PolizaViewModel, navigate: (AppScreens) -> Unit) {
    val isShowAdd by polizaViewModel.showAddPoliza.observeAsState(false)
    val paramsPolizas: PolizaMainParamsItem by polizaViewModel.polizaParamUpdate.observeAsState(
        PolizaMainParamsItem()
    )
    if (paramsPolizas.show) {
        CreatePolizaScreen(
            msg = "",
            titulo = stringResource(id = R.string.LabelUpdatePoliza),
            icon = Icons.Filled.Edit,
            polizaParams = paramsPolizas,
            onClickAcceptButton = {
                polizaViewModel.closeCreatePolizaDialog()
                polizaViewModel.obtenerPolizas(navigate)
            },
            onClickCancelButton = { polizaViewModel.closeUpdatePolizaDialog() })
    }
    if (isShowAdd) CreatePolizaScreen(
        msg = "",
        titulo = stringResource(id = R.string.LabelCreatePoliza),
        icon = Icons.Filled.Add,
        polizaParams = PolizaMainParamsItem(),
        {
            polizaViewModel.closeCreatePolizaDialog()
            polizaViewModel.obtenerPolizas(navigate)
        },
        { polizaViewModel.closeCreatePolizaDialog() })
}

@Composable
fun Footer(modifier: Modifier) {
    Box(modifier = modifier)

}