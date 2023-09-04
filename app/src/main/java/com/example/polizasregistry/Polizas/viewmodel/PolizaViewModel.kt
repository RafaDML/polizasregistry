package com.example.polizasregistry.Polizas.viewmodel

import androidx.lifecycle.ViewModel
import com.example.polizasregistry.Polizas.domain.usecase.ObtenerPolizasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PolizaViewModel @Inject constructor(
    private var obtenerPolizasUseCase: ObtenerPolizasUseCase
): ViewModel() {

    fun obtenerPolizas (
    ){

    }
}