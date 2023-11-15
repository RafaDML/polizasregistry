package com.polizas.polizasregistry.polizas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.usecase.ObtenerPolizasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PolizaViewModel @Inject constructor(
    private val obtenerPolizasUseCase: ObtenerPolizasUseCase
) : ViewModel() {

    private val _carrito = MutableLiveData<ObtenerPolizasItem>()
    val carrito: LiveData<ObtenerPolizasItem> = _carrito


}