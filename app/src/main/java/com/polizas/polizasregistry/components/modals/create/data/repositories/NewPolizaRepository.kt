package com.polizas.polizasregistry.components.modals.create.data.repositories

import com.polizas.polizasregistry.components.modals.create.data.network.InputPolizaService
import com.polizas.polizasregistry.components.modals.create.data.network.request.EditPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.request.NewPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.response.NewPolizaResponse
import com.polizas.polizasregistry.components.modals.create.models.model.InputPolizaCreateItem
import com.polizas.polizasregistry.components.modals.create.models.model.toDomain
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.network.models.ResponseModel
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NewPolizaRepository @Inject constructor(
    private val api: InputPolizaService
) {
    suspend fun doAddNewPoliza(request: NewPolizaRequest): BaseModel<InputPolizaCreateItem> {
        try {
            val response = api.addNewPoliza(request)
            return getAddPolizaRepository(response)
        } catch (e: Exception) {
            return BaseModel.Error("Error de conexion ${e.message}")
        }
    }

    private fun getAddPolizaRepository(response: Response<ResponseModel<NewPolizaResponse>>): BaseModel<InputPolizaCreateItem> {
        return if (response.code() == 200) {
            val getAddPoliza = response.body()?.data?.toDomain()
            BaseModel.Success(getAddPoliza)
        } else if (response.code() == 403) {
            BaseModel.Error("Se cerrará la sesión", true)
        } else {
            val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
            BaseModel.Error(jsonObj.getString("message"))
        }
    }

    suspend fun doEditPoliza(request: EditPolizaRequest): BaseModel<Boolean> {
        try {
            val response = api.editPoliza(request)
            return getEditPoliza(response)
        } catch (e: Exception) {
            return BaseModel.Error("Error de conexion ${e.message}")
        }
    }

    private fun getEditPoliza(response: Response<ResponseModel<Boolean>>): BaseModel<Boolean> {
        return if(response.code() == 200){
            BaseModel.Success(response.body()?.data)
        }else if (response.code() == 403) {
            BaseModel.Error("Se cerrará la sesión", true)
        }else{
            val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
            BaseModel.Error(jsonObj.getString("message"))        }
    }
}