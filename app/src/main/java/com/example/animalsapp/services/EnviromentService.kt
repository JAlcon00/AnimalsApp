package com.example.animalsapp.services

import com.example.animalsapp.models.EnviromentItem
import retrofit2.http.GET
import retrofit2.http.Path

interface EnviromentService {
    @GET("enviroments")
    suspend fun getEnviroments(): List<EnviromentItem>

    @GET("enviroments/{id}")
    suspend fun getEnviromentById(@Path("id") id: String): EnviromentItem
}