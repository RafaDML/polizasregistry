package com.example.polizasregistry.Polizas.data.repositories

import android.util.Log

import com.coppel.pvm.core.network.models.BaseModel
import com.coppel.pvm.core.network.models.ResponseModel
import com.example.polizasregistry.Polizas.data.network.PolizasService
import com.example.polizasregistry.Polizas.data.network.response.ObtenerPolizasResponse
import com.example.polizasregistry.Polizas.domain.model.ObtenerPolizasItem
import com.example.polizasregistry.Polizas.domain.model.toDomain
import retrofit2.Response
import javax.inject.Inject

class ObtenerPolizasRepository @Inject constructor(
    private val api : PolizasService
) {

    suspend fun obtenerPolizas(
    ): BaseModel<ObtenerPolizasItem> {

        val response = api.obtenerPolizas()
        return getResponseObtenerPolizas(response)
    }

    private fun getResponseObtenerPolizas(response: Response<ResponseModel<ObtenerPolizasResponse>>): BaseModel<ObtenerPolizasItem> {
        if (response.code() == 200) {
            val obtenerPolizasResponse = response.body()?.data?.response?.toDomain()
            val meta = response.body()?.meta
            return if (meta?.status == "ERROR") {
                BaseModel.Error(msg=response.message())
            } else {
                 return BaseModel.Success(obtenerPolizasResponse)
            }
        } else {
            return BaseModel.Error(msg = response.message())
        }
    }


}