package com.polizas.polizasregistry.scaffold.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.navigation.AppScreens
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.usecase.ObtenerPolizasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private var obtenerPolizasUseCase: ObtenerPolizasUseCase,

    ) : ViewModel() {
    private var _titulo = MutableLiveData("Inicio")
    val titulo: LiveData<String> = _titulo

    private var _msg = MutableLiveData("Inicio")
    val msg: LiveData<String> = _msg

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _ruta = MutableLiveData<AppScreens>(AppScreens.PolizaScreen)
    val ruta: LiveData<AppScreens> = _ruta

    private var _polizasItems = MutableLiveData<ObtenerPolizasItem>()
    val polizasItem: LiveData<ObtenerPolizasItem> = _polizasItems

    fun changeTitulo(route: AppScreens) {
        _ruta.value = route
        _titulo.value = when (route.route) {
            "poliza_screen" -> "Polizas"
            else -> "INICIO"
        }
    }

    fun logout(navigate: (AppScreens) -> Unit){
        SessionTokenManager.clearData(PolizaApplication.getApplicationContext()!!)
        navigate(AppScreens.LoginScreen)
    }

    fun obtenerPolizas(
    ) {
        viewModelScope.launch() {
            _isLoading.value = true
            when (val result = obtenerPolizasUseCase.obtenerPoliza()
            ) {
                is BaseModel.Error -> {
                    Log.i("RAFA", "ERROR BASE MODEL")

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