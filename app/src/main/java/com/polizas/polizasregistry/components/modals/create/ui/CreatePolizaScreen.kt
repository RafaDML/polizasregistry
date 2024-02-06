package com.polizas.polizasregistry.components.modals.create.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.polizasregistry.R
import com.polizas.polizasregistry.components.buttons.mainbutton.ui.ButtonType
import com.polizas.polizasregistry.components.buttons.mainbutton.ui.MainButton
import com.polizas.polizasregistry.components.forms.textfield.ui.InputScreen
import com.polizas.polizasregistry.components.modals.create.models.model.PolizaMainParamsItem
import com.polizas.polizasregistry.components.modals.create.viewmodel.CreatePolizaViewModel
import com.polizas.polizasregistry.components.modals.dialog.ui.DialogScreen
import com.polizas.polizasregistry.components.modals.dialogoptions.models.DialogOptionsItem
import com.polizas.polizasregistry.components.modals.dialogoptions.ui.DialogOptionsScreen
import com.polizas.polizasregistry.components.modals.loading.ui.LoadingScreen

@Composable
fun CreatePolizaScreen(
    msg: String,
    titulo: String,
    icon: ImageVector,
    polizaParams: PolizaMainParamsItem,
    onClickAcceptButton: () -> Unit,
    onClickCancelButton: () -> Unit,
    createPolizaViewModel: CreatePolizaViewModel = hiltViewModel(),
) {
    val isEdit =
        (polizaParams.sku.isNotEmpty() && polizaParams.numemp.isNotEmpty() && polizaParams.numCantidad.isNotEmpty())
    LaunchedEffect(Unit) {
        if (isEdit) createPolizaViewModel.initParamsData(polizaParams)
    }
    val isLoading: Boolean by createPolizaViewModel.isLoading.observeAsState(initial = false)
    val msg: String by createPolizaViewModel.msg.observeAsState(msg)
    val showDialogOptions: DialogOptionsItem by createPolizaViewModel.dialogInfoOptions.observeAsState(
        DialogOptionsItem()

    )
    if (showDialogOptions.show) {
        if (showDialogOptions.options == 2) {
            Log.i("RAFA", "OPTIONS 2 $icon")
            DialogOptionsScreen(
                msg = showDialogOptions.msg,
                icon = showDialogOptions.icon,
                onClickAcceptButton = { },
                onClickCancelButton = {
                    createPolizaViewModel.closeCreatePolizaDialog()
                })
        } else {
            Log.i("RAFA", "OPTIONS 1")

            DialogScreen(
                showDialogOptions.msg,
                showDialogOptions.icon,
            ) {
                if (showDialogOptions.status) {
                    onClickAcceptButton()
                    createPolizaViewModel.closeCreatePolizaDialog()
                } else {
                    createPolizaViewModel.closeFailedPolizaDialog()
                }
            }
        }
    }
    if (isLoading) {
        LoadingScreen(msg = msg)
    }
    CreatePolizaBody(createPolizaViewModel, titulo,onClickCancelButton, isEdit, polizaParams)
}

@Composable
fun CreatePolizaBody(
    createPolizaViewModel: CreatePolizaViewModel,
    titulo: String,
    onClickCancelButton: () -> Unit,
    isEdit: Boolean,
    polizaParams: PolizaMainParamsItem
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false, dismissOnBackPress = false

        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .fillMaxWidth(),
            ) {

                val sku: String by createPolizaViewModel.sku.observeAsState(initial = "")
                val numCantidad: String by createPolizaViewModel.numCantidad.observeAsState(initial = "")
                val numEmp: String by createPolizaViewModel.numEmp.observeAsState(initial = "")
                val isEnabled: Boolean by createPolizaViewModel.isCreateEnabled.observeAsState(false)
                Column {
                    Row(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, bottom = 5.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White),
                                text = titulo,
                                style = TextStyle(
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                            IconButton(onClick = {
                                onClickCancelButton.invoke()
                                createPolizaViewModel.closeCreatePolizaDialog()
                            }) {
                                Icon(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(top = 5.dp),
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(25.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            textAlign = TextAlign.Start,
                            text = stringResource(id = R.string.PolizaSkuLabel),
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,

                            )
                        InputScreen(
                            VisualTransformation.None,
                            sku,
                            KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        ) {
                            createPolizaViewModel.validateFieldRestrictions(
                                it,
                                "sku"
                            )
                        }

                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            text = stringResource(id = R.string.PolizaCantidadLabel),
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                        InputScreen(
                            VisualTransformation.None,
                            numCantidad,
                            KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        ) {
                            createPolizaViewModel.validateFieldRestrictions(
                                it,
                                "numCantidad"
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            text = stringResource(id = R.string.PolizaEmpleadoLabel),
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                        InputScreen(
                            VisualTransformation.None,
                            numEmp,
                            KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )

                        ) {
                            createPolizaViewModel.validateFieldRestrictions(
                                it,
                                "numEmpleado"
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            MainButton(
                                isEnabled,
                                {
                                    if (!isEdit) {
                                        createPolizaViewModel.launchCreatePoliza(
                                            sku,
                                            numCantidad,
                                            numEmp
                                        )
                                    } else {
                                        polizaParams.sku = sku
                                        polizaParams.numemp = numEmp
                                        polizaParams.numCantidad = numCantidad
                                        createPolizaViewModel.launchEditPoliza(polizaParams)
                                    }
                                },
                                ButtonType.Affirmative
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                MainButton(
                                    true,
                                    {
                                        onClickCancelButton.invoke()
                                        createPolizaViewModel.closeCreatePolizaDialog()
                                    },
                                    ButtonType.Negative
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}