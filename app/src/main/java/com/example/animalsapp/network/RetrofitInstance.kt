// app/src/main/java/com/example/animalsapp/network/RetrofitInstance.kt
package com.example.animalsapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.animalsapp.services.AnimalsService
import com.example.animalsapp.services.EnviromentService

object RetrofitInstance {
    private const val BASE_URL = "https://animals.juanfrausto.com/api/"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val animalsService: AnimalsService =
        retrofit.create(AnimalsService::class.java)

    val envService: EnviromentService =
        retrofit.create(EnviromentService::class.java)
}
