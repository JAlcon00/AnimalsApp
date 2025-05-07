package com.example.animalsapp.services

import com.example.animalsapp.models.AnimalsList
import retrofit2.http.GET


interface AnimalsService {
    @GET("animals")
    suspend fun getAnimals(): AnimalsList

    @GET("animals")
    suspend fun getAnimalsById(): AnimalsList
}