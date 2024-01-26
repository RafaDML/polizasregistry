package com.polizas.polizasregistry.core.network.interceptors

import android.util.Log
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

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
            var responseBody: ResponseBody
            var statusCode: Int
            var message: String
            try {
                val response = chain.proceed(request)
                responseBody = response.body()!!
                statusCode = response.code()
                message = response.message()

            } catch (ex: Exception) {
                responseBody =
                    ResponseBody.create(MediaType.get("application/json; charset=utf-8"), "{$ex}")
                statusCode = 500
                message = ex.message.toString()
            }
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(statusCode)
                .message(message)
                .body(responseBody).build()
        }
    }
}