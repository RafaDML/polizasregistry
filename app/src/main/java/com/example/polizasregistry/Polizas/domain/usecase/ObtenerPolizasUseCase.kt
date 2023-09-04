package com.example.polizasregistry.Polizas.domain.usecase

import com.coppel.pvm.core.network.models.BaseModel
import com.example.polizasregistry.Polizas.data.network.PolizasClient
import com.example.polizasregistry.Polizas.data.network.response.ObtenerPolizasResponse
import com.example.polizasregistry.Polizas.data.repositories.ObtenerPolizasRepository
import com.example.polizasregistry.Polizas.domain.model.ObtenerPolizasItem
import retrofit2.Response
import javax.inject.Inject

class ObtenerPolizasUseCase @Inject constructor(
    private val obtenerPolizasRepositoy: ObtenerPolizasRepository
) {


    suspend fun obtenerPoliza(): BaseModel<ObtenerPolizasItem> {
        return obtenerPolizasRepositoy.obtenerPolizas();
    }


}