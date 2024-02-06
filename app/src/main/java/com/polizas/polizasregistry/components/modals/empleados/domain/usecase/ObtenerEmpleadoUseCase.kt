package com.polizas.polizasregistry.components.modals.empleados.domain.usecase

import com.polizas.polizasregistry.components.modals.empleados.data.repositories.EmpleadoRepository
import com.polizas.polizasregistry.components.modals.empleados.domain.model.ObtenerEmpleadosItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import javax.inject.Inject

class ObtenerEmpleadoUseCase @Inject constructor(
    private val empleadoRepository: EmpleadoRepository
){

    suspend fun obtenerEmpleados(): BaseModel<ObtenerEmpleadosItem>{
        return empleadoRepository.obtenerEmpleados()
    }
}