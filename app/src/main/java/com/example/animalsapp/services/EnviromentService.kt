package com.example.animalsapp.services

import com.example.animalsapp.models.EnviromentList
import retrofit2.http.GET
import retrofit2.http.Path

interface EnviromentService {
    @GET("enviroments")
    suspend fun getEnviroments(): EnviromentList

    @GET("enviroment/s{id}")
    suspend fun getEnviromentById(@Path("id") id: String): EnviromentList
}