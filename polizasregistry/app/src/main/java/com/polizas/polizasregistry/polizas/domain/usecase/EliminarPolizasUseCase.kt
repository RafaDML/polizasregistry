package com.polizas.polizasregistry.polizas.domain.usecase

import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.polizas.data.repositories.ObtenerPolizasRepository
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import javax.inject.Inject

class EliminarPolizasUseCase @Inject constructor(
    private val obtenerPolizasRepositoy: ObtenerPolizasRepository
) {
    suspend fun eliminarPoliza(idPoliza: Int?): BaseModel<String> {
        return obtenerPolizasRepositoy.eliminarPoliza(idPoliza);
    }
}