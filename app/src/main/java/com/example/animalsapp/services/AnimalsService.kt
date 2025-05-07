package com.example.animalsapp.services

import com.example.animalsapp.models.Animals
import retrofit2.http.GET


interface AnimalsService {
    @GET("animals")
    suspend fun getAnimals(): Animals

    @GET("animals")
    suspend fun getAnimalsById(): Animals
}