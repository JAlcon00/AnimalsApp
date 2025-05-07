package com.example.animalsapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://animals.juanfrausto.com/api/animals/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val animalsService: com.example.animalsapp.services.AnimalsService by lazy {
        retrofit.create(com.example.animalsapp.services.AnimalsService::class.java)
    }

    val enviromentService: com.example.animalsapp.services.EnviromentService by lazy {
        retrofit.create(com.example.animalsapp.services.EnviromentService::class.java)
    }
}