package com.polizas.polizasregistry.core.sessionmanagers

import android.content.Context
import android.content.SharedPreferences
import com.example.polizasregistry.R

object SessionTokenManager {

    const val TOKEN_LOGGED = "token"

    /**
     * Function to save auth token
     */
    fun saveToken(context: Context, token: String) {
        saveString(context, TOKEN_LOGGED, token)
    }

    /**
     * Function to fetch auth token
     */
    fun getToken(context: Context): String? {
        return getString(context, TOKEN_LOGGED)
    }

    fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    @Suppress("UNUSED_PARAMETER")
    fun getString(context: Context, key: String): String? {

        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(this.TOKEN_LOGGED, null)
    }

    fun clearData(context: Context) {
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
            .edit().remove(
                TOKEN_LOGGED
            ).apply()
    }
}