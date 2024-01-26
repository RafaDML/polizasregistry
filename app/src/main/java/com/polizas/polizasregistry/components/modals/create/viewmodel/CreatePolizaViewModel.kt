package com.polizas.polizasregistry.components.modals.create.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.components.modals.create.data.network.request.EditPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.request.NewPolizaRequest
import com.polizas.polizasregistry.components.modals.create.models.model.PolizaMainParamsItem
import com.polizas.polizasregistry.components.modals.create.models.usecase.InputPolizaUseCase
import com.polizas.polizasregistry.components.modals.dialogoptions.models.DialogOptionsItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreatePolizaViewModel @Inject constructor(
    private val inputPolizaUseCase: InputPolizaUseCase
) : ViewModel() {

    private val _sku = MutableLiveData("")
    val sku: LiveData<String> = _sku

    private val _numCantidad = MutableLiveData<String>()
    val numCantidad: LiveData<String> = _numCantidad

    private val _numEmp = MutableLiveData<String>()
    val numEmp: LiveData<String> = _numEmp

    private val _isCreateEnabled = MutableLiveData<Boolean>()
    val isCreateEnabled: LiveData<Boolean> = _isCreateEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _dialogInfoOptions = MutableLiveData<DialogOptionsItem>()
    val dialogInfoOptions: LiveData<DialogOptionsItem> = _dialogInfoOptions

    fun validateFieldRestrictions(value: String, field: String) {
        var regExpValidation = ".".toRegex()
        when (field) {
            "sku" -> regExpValidation = "^\\d{0,6}\$".toRegex()
            "numCantidad" -> regExpValidation = "^\\d{0,4}\$".toRegex()
            "numEmpleado" -> regExpValidation = "^\\d{0,8}\$".toRegex()
        }
        Log.i("RAFA", "VALOR REGEXP $regExpValidation")
        if (value != null && value.matches(regExpValidation)) {
            Log.i("RAFA", "MATCHES REGEXP")
            setFormularioCreate(value, field)
            enableCreateButton()
        }
    }

    fun initParamsData(polizaParams: PolizaMainParamsItem) {
        setFormularioCreate(polizaParams.sku, "sku")
        setFormularioCreate(polizaParams.numCantidad, "numCantidad")
        setFormularioCreate(polizaParams.numemp, "numEmpleado")
        enableCreateButton()
    }

    fun setFormularioCreate(value: String, field: String) {
        when (field) {
            "sku" -> _sku.value = value
            "numCantidad" -> _numCantidad.value = value
            "numEmpleado" -> _numEmp.value = value
        }
    }

    fun enableCreateButton() {
        val cantidad = if (!_numCantidad.value.isNullOrEmpty()) _numCantidad.value!!.toInt() else 0

        _isCreateEnabled.value =
            _sku.value?.length == 6 && cantidad > 0 && _numEmp.value?.length == 8

    }

    fun launchEditPoliza(polizaParams: PolizaMainParamsItem) {
        _dialogInfoOptions.value = DialogOptionsItem(false, "", Icons.Filled.Info)
        _isLoading.value = true
        _msg.value = "Cargando"

        viewModelScope.launch {
            delay(3000)
            val request = EditPolizaRequest(
                polizaParams.id,
                polizaParams.sku.toInt(),
                polizaParams.numemp.toInt(),
                polizaParams.numCantidad.toInt(),
                polizaParams.fecha
            )
            var dialogResult = DialogOptionsItem(
            show = true,
            icon = Icons.Filled.Info,
            msg = "",
            options = 1
        )
            when (val result = inputPolizaUseCase.doInputPolizaEdit(request)) {
                is BaseModel.Success -> {
                    dialogResult.icon = Icons.Filled.Check
                    dialogResult.msg = "Se actualizó la póliza "
                    dialogResult.status = true
                }

                is BaseModel.Error -> {
                    dialogResult.icon = Icons.Filled.Error
                    dialogResult.msg = result.msg.toString()
                }
            }
            _isLoading.value = false
            _dialogInfoOptions.value = dialogResult
        }
    }

    fun launchCreatePoliza(sku: String, numCantidad: String, numEmp: String) {
        _dialogInfoOptions.value = DialogOptionsItem(false, "", Icons.Filled.Info)
        _isLoading.value = true
        _msg.value = "Cargando"

        viewModelScope.launch {

            delay(3000)
            val currentDate = Date()
            Log.i("RAFA", "CURRENT DATE $currentDate")
            // Format the date as a string (optional)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val formattedDate = dateFormat.format(currentDate)
            val request =
                NewPolizaRequest(
                    empleado = numEmp.toInt(),
                    sku = sku.toInt(),
                    cantidad = numCantidad.toInt(),
                    fecha = formattedDate
                )
            var dialogResult = DialogOptionsItem(
                show = true,
                icon = Icons.Filled.Info,
                msg = "",
                options = 1
            )
            when (val result = inputPolizaUseCase.doInputPolizaCreate(request)) {
                is BaseModel.Success -> {
                    dialogResult.icon = Icons.Filled.Check
                    dialogResult.msg = "Se grabó la póliza No." + result.data?.idPoliza.toString()
                    dialogResult.status = true
                }

                is BaseModel.Error -> {
                    dialogResult.icon = Icons.Filled.Error
                    dialogResult.msg = result.msg.toString()
                }
            }
            _isLoading.value = false
            _dialogInfoOptions.value = dialogResult
        }
    }

    fun closeCreatePolizaDialog() {
        _dialogInfoOptions.value = DialogOptionsItem(show = false)
        cleanValues()
    }

    fun closeFailedPolizaDialog() {
        _dialogInfoOptions.value = DialogOptionsItem(show = false)
    }

    fun cleanValues() {
        _sku.value = ""
        _numCantidad.value = ""
        _numEmp.value = ""
        _isCreateEnabled.value = false
    }
}