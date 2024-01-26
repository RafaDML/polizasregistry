package com.polizas.polizasregistry.core.api

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.core.network.interceptors.InterceptorPrincipal
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.polizas.polizasregistry.utils.Constants
import okhttp3.OkHttpClient

class ApiRestClient {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .client(getClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val token = SessionTokenManager.getToken(PolizaApplication.getApplicationContext()!!)
        Log.i("RAFA ", "INTERCEPTOR TOKEN $token")
       return OkHttpClient.Builder()
            .addInterceptor(InterceptorPrincipal())
            .build()
    }
}