package com.polizas.polizasregistry.components.modals.inventario.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.components.modals.inventario.domain.model.ObtenerInventarioItem
import com.polizas.polizasregistry.components.modals.inventario.domain.usecase.ObtenerInventarioUseCase
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventarioViewModel @Inject constructor(
    private val inventarioUseCase: ObtenerInventarioUseCase
):ViewModel(){

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _msg = MutableLiveData<String>("Cargando")
    val msg: LiveData<String> = _msg

    private val _dialogInfo = MutableLiveData<DialogItem>()

    private var _inventarioItem = MutableLiveData<ObtenerInventarioItem>()
    val inventarioItem: LiveData<ObtenerInventarioItem> = _inventarioItem


    fun obtenerInventario(
        navigate: (AppScreens) -> Unit,
    ) {
        viewModelScope.launch {
            _msg.value = "Cargando"
            _isLoading.value = true
            delay(2000)
            when (val result = inventarioUseCase.obtenerInventario()
            ) {
                is BaseModel.Error -> {
                    Log.i(
                        "RAFA",
                        "ERROR BASE MODEL para obtener inventario ${result.msg} code ${result.cerrarSesion}"
                    )
                    if (result.cerrarSesion) {
                        SessionTokenManager.clearData(PolizaApplication.getApplicationContext()!!)
                        _msg.value = "Se cerrará sesión"
                        navigate(AppScreens.LoginScreen)
                    } else {
                        _dialogInfo.value =
                            DialogItem(true, result.msg.toString(), Icons.Filled.Error)
                    }
                }

                is BaseModel.Success -> {
                    Log.i("RAFA", "SUCCESS BASE MODEL ")
                    if (result.data != null) {
                        _inventarioItem.value = result.data

                        Log.i("VIEWMODEL ", " DATO FINAL ${_inventarioItem.value?.inventario?.size}")
                    }
                }
            }
            _isLoading.value = false
        }
    }
}