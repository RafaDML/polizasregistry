package com.polizas.polizasregistry.components.modals.empleados.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.components.modals.empleados.domain.model.ObtenerEmpleadosItem
import com.polizas.polizasregistry.components.modals.empleados.domain.usecase.ObtenerEmpleadoUseCase
import com.polizas.polizasregistry.components.modals.inventario.domain.model.ObtenerInventarioItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpleadoViewModel @Inject constructor(
    private val empleadoUseCase: ObtenerEmpleadoUseCase
):ViewModel(){

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _msg = MutableLiveData<String>("Cargando")
    val msg: LiveData<String> = _msg

    private val _dialogInfo = MutableLiveData<DialogItem>()
    val dialogInfo: LiveData<DialogItem> = _dialogInfo

    private var _empleadosValue = MutableLiveData<ObtenerEmpleadosItem>()
    val empleadosValue: LiveData<ObtenerEmpleadosItem> = _empleadosValue


    fun obtenerInventario(
        navigate: (AppScreens) -> Unit,
    ) {
        viewModelScope.launch {
            _msg.value = "Cargando"
            _isLoading.value = true
            delay(2000)
            when (val result = empleadoUseCase.obtenerEmpleados()
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
                        _empleadosValue.value = result.data

                        Log.i("VIEWMODEL ", " DATO FINAL ${_empleadosValue.value?.empleados?.size}")
                    }
                }
            }
            _isLoading.value = false
        }
    }
}