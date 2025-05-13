package com.example.animalsapp.network

import com.example.animalsapp.services.AnimalsService
import com.example.animalsapp.services.EnviromentService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://animals.juanfrausto.com/api/"

    // 1. Creamos el interceptor que loguea cuerpo de request/response
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // 2. Lo añadimos al cliente OkHttp
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    // 3. Configuramos Retrofit con ese cliente
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 4. Creamos los servicios
    val animalsService: AnimalsService =
        retrofit.create(AnimalsService::class.java)

    val envService: EnviromentService =
        retrofit.create(EnviromentService::class.java)
}
