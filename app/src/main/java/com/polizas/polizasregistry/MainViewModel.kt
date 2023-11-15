package com.polizas.polizasregistry

import android.util.Log
import androidx.lifecycle.ViewModel
import com.polizas.polizasregistry.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun activeSession(navigate : (AppScreens) -> Unit){
        Log.i("try to navigate","scaffold")
        navigate(AppScreens.ScaffoldScreen)
    }

}