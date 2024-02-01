package com.polizas.polizasregistry.components.modals.empleados.data.network

import com.polizas.polizasregistry.components.modals.empleados.data.network.response.ObtenerEmpleadosResponse
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface EmpleadoClient {
    @GET("empleado")
    suspend fun obtenerEmpleados(): Response<ResponseModel<ObtenerEmpleadosResponse>>
}