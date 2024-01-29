package com.polizas.polizasregistry.components.modals.empleados.data.network

import com.polizas.polizasregistry.components.modals.empleados.data.network.response.ObtenerEmpleadosResponse
import com.polizas.polizasregistry.components.modals.inventario.data.network.response.ObtenerInventarioResponse
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import javax.inject.Inject

class EmpleadoService @Inject constructor(
    private val empleadoClient: EmpleadoClient
) {

    suspend fun obtenerEmpleados(/*request*/): Response<ResponseModel<ObtenerEmpleadosResponse>> {
        return empleadoClient.obtenerEmpleados()
    }

}