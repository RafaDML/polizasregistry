package com.polizas.polizasregistry.scaffold.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QuestionMark
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.navigation.AppScreens
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.usecase.ObtenerPolizasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private var obtenerPolizasUseCase: ObtenerPolizasUseCase,

    ) : ViewModel() {
    private var _titulo = MutableLiveData("Inicio")
    val titulo: LiveData<String> = _titulo

    private var _msg = MutableLiveData("Cargando")
    val msg: LiveData<String> = _msg

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _ruta = MutableLiveData<AppScreens>(AppScreens.PolizaScreen)
    val ruta: LiveData<AppScreens> = _ruta

    private  var _idDelete = MutableLiveData(0)
    val idDelete : LiveData<Int> = _idDelete

    private var _polizasItems = MutableLiveData<ObtenerPolizasItem>()
    val polizasItem: LiveData<ObtenerPolizasItem> = _polizasItems

    private val _dialogInfo = MutableLiveData<DialogItem>()
    val dialogInfo: LiveData<DialogItem> = _dialogInfo

    private val _dialogInfoOptions = MutableLiveData<DialogItem>()
    val dialogInfoOptions: LiveData<DialogItem> = _dialogInfoOptions

    fun changeTitulo(route: AppScreens) {
        _ruta.value = route
        _titulo.value = when (route.route) {
            "poliza_screen" -> "Polizas"
            else -> "INICIO"
        }
    }

    fun logout(navigate: (AppScreens) -> Unit) {
        SessionTokenManager.clearData(PolizaApplication.getApplicationContext()!!)
        navigate(AppScreens.LoginScreen)
    }

    fun closeModal(
        show: Boolean = false, msg: String = ""
    ) {
        _dialogInfo.value = DialogItem(false, "", Icons.Filled.Info)
        _dialogInfoOptions.value = DialogItem(false,"",Icons.Filled.Info)
    }

    fun launchDeletePolizas(msg: String, dato: Int?){
        _idDelete.value = dato
        launchDialogOptions(msg)
    }
    fun launchDialogOptions(msg: String) {
        _dialogInfoOptions.value = DialogItem(true,msg, Icons.Filled.QuestionMark)
    }



    fun obtenerPolizas(
        navigate: (AppScreens) -> Unit,
    ) {
        viewModelScope.launch() {
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
}