package com.polizas.polizasregistry.components.modals.inventario.domain.usecase

import com.polizas.polizasregistry.components.modals.inventario.data.repositories.InventarioRepository
import com.polizas.polizasregistry.components.modals.inventario.domain.model.ObtenerInventarioItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import javax.inject.Inject

class ObtenerInventarioUseCase @Inject constructor(
    private val inventarioRepository: InventarioRepository
) {
    suspend fun obtenerInventario(): BaseModel<ObtenerInventarioItem> {
        return inventarioRepository.obtenerInventario()
    }

}