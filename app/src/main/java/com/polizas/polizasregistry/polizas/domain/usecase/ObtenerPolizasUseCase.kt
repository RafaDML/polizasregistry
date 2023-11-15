package com.polizas.polizasregistry.polizas.domain.usecase

import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.polizas.data.repositories.ObtenerPolizasRepository
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import javax.inject.Inject

class ObtenerPolizasUseCase @Inject constructor(
    private val obtenerPolizasRepositoy: ObtenerPolizasRepository
) {


    suspend fun obtenerPoliza(): BaseModel<ObtenerPolizasItem> {
        return obtenerPolizasRepositoy.obtenerPolizas();
    }


}