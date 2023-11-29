package com.polizas.polizasregistry.login.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.login.domain.usecase.LoginUseCase
import com.polizas.polizasregistry.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _dialogInfo = MutableLiveData<DialogItem>()
    val dialogInfo: LiveData<DialogItem> = _dialogInfo

    fun entrarScaffold(
        navigate: (AppScreens) -> Unit,
    ) {
        val opensesion : Boolean

        val token = SessionTokenManager.getToken(PolizaApplication.getApplicationContext()!!)

       if(token != null){
           navigate(AppScreens.ScaffoldScreen)
       }

    }

    fun onLoginSelect(
        loginViewModel: LoginViewModel,
        username: String,
        password: String,
        navigate: (AppScreens) -> Unit,
    ) {
        viewModelScope.launch() {
            _isLoading.value = true
            delay(2000)

            when (val result = loginUseCase.doLogin(
                username,
                password
            )) {
                is BaseModel.Error -> {
                    Log.i("RAFA TOKEN FAILURE", "a")
                    _dialogInfo.value =
                        DialogItem(true, "Error de Inicio de Sesión", Icons.Filled.Error)
                }

                is BaseModel.Success -> {
                    Log.i("RAFA TOKEN SUCCESS", "a")
                    navigate(AppScreens.ScaffoldScreen)
                }
            }
            _isLoading.value = false

        }

        val logeo = SessionTokenManager.getToken(PolizaApplication.getApplicationContext()!!)

        if (logeo != null) {
            Log.i("RAFA TOKEN", logeo)
        }

    }

    fun closeModal(
        show: Boolean = false, msg: String = ""
    ) {
        _dialogInfo.value = DialogItem(false, "", Icons.Filled.Info)
    }

    fun onChangeUser(username: String, password: String) {
        val emailPattern = Regex("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+")

        _username.value = username
        _password.value = password

        _isButtonEnabled.value =
            (emailPattern.matches(_username.value.toString()) && _password.value!!.length > 3)
        Log.i("RAFA VALOR BUTTON", _isButtonEnabled.value.toString())
    }
}