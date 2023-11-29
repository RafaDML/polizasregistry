package com.polizas.polizasregistry

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.provider.Settings
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class PolizaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: PolizaApplication
            private set
        fun getApplicationContext(): Context? {
            return instance.applicationContext
        }
        @SuppressLint("HardwareIds")
        fun getApplicationId(): String {
            return Settings.Secure.getString(getApplicationContext()?.contentResolver, Settings.Secure.ANDROID_ID)
        }
    }

}