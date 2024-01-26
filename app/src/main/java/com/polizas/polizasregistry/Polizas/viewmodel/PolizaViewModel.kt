package com.polizas.polizasregistry.polizas.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.components.modals.create.models.model.PolizaMainParamsItem
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.components.modals.dialogoptions.models.DialogOptionsItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.navigation.AppScreens
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.usecase.EliminarPolizasUseCase
import com.polizas.polizasregistry.polizas.domain.usecase.ObtenerPolizasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PolizaViewModel @Inject constructor(
    private val eliminarPolizasUseCase: EliminarPolizasUseCase,
    private val obtenerPolizasUseCase: ObtenerPolizasUseCase
) : ViewModel() {

    private val _carrito = MutableLiveData<ObtenerPolizasItem>()
    val carrito: LiveData<ObtenerPolizasItem> = _carrito

    private val _dialogInfo = MutableLiveData<DialogItem>()
    val dialogInfo: LiveData<DialogItem> = _dialogInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _msg = MutableLiveData<String>("Cargando")
    val msg: LiveData<String> = _msg

    private var _idDelete = MutableLiveData(0)
    val idDelete: LiveData<Int> = _idDelete

    private var _polizasItems = MutableLiveData<ObtenerPolizasItem>()
    val polizasItem: LiveData<ObtenerPolizasItem> = _polizasItems

    private val _dialogInfoOptions = MutableLiveData<DialogOptionsItem>()
    val dialogInfoOptions: LiveData<DialogOptionsItem> = _dialogInfoOptions

    private val _showAddPoliza = MutableLiveData(false)
    val showAddPoliza: LiveData<Boolean> = _showAddPoliza

    private val _polizaParamUpdate = MutableLiveData<PolizaMainParamsItem>()
    val polizaParamUpdate: LiveData<PolizaMainParamsItem> = _polizaParamUpdate

    private val _showDeletePoliza = MutableLiveData(false)
    val showDeletePoliza: LiveData<Boolean> = _showDeletePoliza

    private val _showEditPoliza = MutableLiveData(false)
    val showEditPoliza: LiveData<Boolean> = _showEditPoliza

    private val _showInfoDialog = MutableLiveData(false)
    val showInfoDialog: LiveData<Boolean> = _showInfoDialog

    fun obtenerPolizas(
        navigate: (AppScreens) -> Unit,
    ) {
        viewModelScope.launch {
            _msg.value = "Cargando"
            _isLoading.value = true
            delay(2000)
            when (val result = obtenerPolizasUseCase.obtenerPoliza()
            ) {
                is BaseModel.Error -> {
                    Log.i(
                        "RAFA",
                        "ERROR BASE MODEL para obtener polizas ${result.msg} code ${result.cerrarSesion}"
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
                    Log.i("RAFA", "SUCCESS BASE MODEL ${result.toString()}")
                    if (result.data != null) {
                        _polizasItems.value = result.data

                        Log.i("VIEWMODEL ", " DATO FINAL ${_polizasItems.value?.polizas?.size}")
                    }
                }
            }

            _isLoading.value = false
        }
    }

    fun eliminarPoliza(navigate: (AppScreens) -> Unit) {
        _isLoading.value = true
        _showDeletePoliza.value = false

        viewModelScope.launch {
            delay(2000)
            var resultmsg: String
            when (val result = eliminarPolizasUseCase.eliminarPoliza(_idDelete.value)
            ) {
                is BaseModel.Success -> {
                    resultmsg = result.data.toString()
                    Log.i("RAFA", "ELIMINAR SUCCESS ${result.data}")
                }

                is BaseModel.Error -> {
                    resultmsg = result.msg.toString()
                    Log.i("RAFA", "ELIMINAR SUCCESS ${result.msg}")
                }
            }
            _msg.value = resultmsg
            _showInfoDialog.value = true
            _isLoading.value = false
        }
    }

    fun closeModal(
    ) {
        _showDeletePoliza.value = false
        _showInfoDialog.value = false
    }

    fun showAddPolizaModal(navigate: (AppScreens) -> Unit) {
        navigate(AppScreens.CreatePolizaScreen)
    }

    fun launchDeletePolizasNew(
        idPoliza: Int?,
        navigate: (AppScreens) -> Unit
    ) {
        _showDeletePoliza.value = true
        _idDelete.value = idPoliza
        Log.i("RAFA", "Delete poliza ${idDelete.value}")
    }

    fun launchCreatePolizaDialog() {
        _showAddPoliza.value = true
    }

    fun launchUpdatePolizaDialog(idPoliza: Int?,sku: String, numCantidad: String, numemp: String, fecha : String) {
        _polizaParamUpdate.value = PolizaMainParamsItem(
            show = true,
            id = idPoliza,
            sku = sku,
            numCantidad = numCantidad,
            numemp = numemp,
            fecha = fecha
        )
    }

    fun closeCreatePolizaDialog() {
        _showAddPoliza.value = false
        _polizaParamUpdate.value?.show = false

    }

    fun closeUpdatePolizaDialog() {
        _polizaParamUpdate.value = PolizaMainParamsItem(show = false)
    }

}