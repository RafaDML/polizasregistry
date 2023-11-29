package com.polizas.polizasregistry.polizas.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QuestionMark
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.usecase.EliminarPolizasUseCase
import com.polizas.polizasregistry.polizas.domain.usecase.ObtenerPolizasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PolizaViewModel @Inject constructor(
    private val eliminarPolizasUseCase: EliminarPolizasUseCase
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

    private val _dialogInfoOptions = MutableLiveData<DialogItem>()
    val dialogInfoOptions: LiveData<DialogItem> = _dialogInfoOptions

    fun eliminarPoliza(idPoliza: Int?) {
        _dialogInfoOptions.value = DialogItem(false, "", Icons.Filled.Info)
        _isLoading.value = true

        viewModelScope.launch {
            delay(10000)
            Log.i("SERVICE ", "inicia view model pre use case")

            when (val result = eliminarPolizasUseCase.eliminarPoliza(idPoliza)
            ) {
                is BaseModel.Success -> {
                    Log.i("RAFA", "ELIMINAR SUCCESS ${result.data}")
                }

                is BaseModel.Error -> {
                    Log.i("RAFA", "ELIMINAR SUCCESS ${result.msg}")

                }
            }
            _isLoading.value = false

        }

    }

    fun launchDeletePolizas(msg: String, dato: Int?) {
        Log.i("RAFA", "LAUNCH DELETE POLIZA $dato")
        _idDelete.value = dato
        launchDialogOptions(msg)
    }

    fun launchDialogOptions(msg: String) {
        _dialogInfoOptions.value = DialogItem(true, msg, Icons.Filled.QuestionMark)
    }

    fun closeModal(
        show: Boolean = false, msg: String = ""
    ) {
        _dialogInfo.value = DialogItem(false, "", Icons.Filled.Info)
        _dialogInfoOptions.value = DialogItem(false, "", Icons.Filled.Info)
    }


}