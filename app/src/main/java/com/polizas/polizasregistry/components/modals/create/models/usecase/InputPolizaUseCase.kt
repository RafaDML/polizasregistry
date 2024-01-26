package com.polizas.polizasregistry.components.modals.create.models.usecase

import com.polizas.polizasregistry.components.modals.create.data.network.request.EditPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.request.NewPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.repositories.NewPolizaRepository
import com.polizas.polizasregistry.components.modals.create.models.model.InputPolizaCreateItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import javax.inject.Inject

class InputPolizaUseCase @Inject constructor(
    private val newPolizaRepository: NewPolizaRepository
) {
    suspend fun doInputPolizaCreate(request : NewPolizaRequest): BaseModel<InputPolizaCreateItem> {
        return newPolizaRepository.doAddNewPoliza(request)
    }

    suspend fun doInputPolizaEdit(request : EditPolizaRequest): BaseModel<Boolean> {
        return newPolizaRepository.doEditPoliza(request)
    }
}