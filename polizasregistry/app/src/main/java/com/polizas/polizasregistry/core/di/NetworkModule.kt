package com.polizas.polizasregistry.core.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.polizas.polizasregistry.core.network.interceptors.InterceptorPrincipal
import com.polizas.polizasregistry.login.data.network.LoginClient
import com.polizas.polizasregistry.polizas.data.network.PolizasClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import com.polizas.polizasregistry.utils.Constants.URL


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    @Named("api_retrofit")
    fun provideAPIRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(InterceptorPrincipal())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginClient(@Named("api_retrofit") retrofit: Retrofit): LoginClient {
        return retrofit.create(LoginClient::class.java)
    }

    @Singleton
    @Provides
    fun providePolizasClient(@Named("api_retrofit") retrofit: Retrofit): PolizasClient {
        return retrofit.create(PolizasClient::class.java)
    }
}