package com.example.animalsapp.services

import com.example.animalsapp.models.EnviromentItem
import retrofit2.http.GET
import retrofit2.http.Path

interface EnviromentService {
    @GET("environments")
    suspend fun getEnvironments(): List<EnviromentItem>

    @GET("environments/{id}")
    suspend fun getEnvironmentById(@Path("id") id: String): EnviromentItem
}
