package com.polizas.polizasregistry.components.modals.create.data.network

import android.util.Log
import com.polizas.polizasregistry.components.modals.create.data.network.request.EditPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.request.NewPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.response.NewPolizaResponse
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import javax.inject.Inject

class InputPolizaService @Inject constructor(
    private val polizaClient: InputPolizaClient,
) {
    suspend fun addNewPoliza(request: NewPolizaRequest): Response<ResponseModel<NewPolizaResponse>> {
        val valor = polizaClient.addNewPoliza(request)
        Log.i("RAFA", "SERVICE ${valor.body()}")
        Log.i("RAFA", "SERVICE ${valor.body()?.message}")
        return valor
    }

    suspend fun editPoliza(request: EditPolizaRequest): Response<ResponseModel<Boolean>> {
        val valor = polizaClient.editPoliza(request)
        return valor
    }

}