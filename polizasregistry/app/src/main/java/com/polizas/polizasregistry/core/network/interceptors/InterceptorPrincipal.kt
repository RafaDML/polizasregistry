package com.polizas.polizasregistry.core.network.interceptors

import android.util.Log
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import okhttp3.Interceptor
import okhttp3.Response

class InterceptorPrincipal : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SessionTokenManager.getToken(PolizaApplication.getApplicationContext()!!)
        Log.i("RAFA ", "INTERCEPTOR TOKEN $token")
        val originalRequest = chain.request()
        val segments: List<String> = originalRequest.url().pathSegments()
        var loginrequest = false
        segments.forEachIndexed { index, path ->
            Log.i("RAFA ", "INTERCEPTOR TOKEN $path , $index")

            loginrequest = path == "login"
        }

        return if (loginrequest) {
            Log.i("RAFA ", "ENTRO A LOGIN")

            chain.proceed(originalRequest)
        } else {
            Log.i("RAFA ", "ENTRO AL QUE NO ERA LOGIN")

            val request = originalRequest.newBuilder()
                .addHeader(
                    "Authorization", "Bearer $token"
                ).build()
            chain.proceed(request)
        }
    }
}