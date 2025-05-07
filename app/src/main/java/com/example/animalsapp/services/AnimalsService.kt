package com.example.animalsapp.services

import com.example.animalsapp.models.AnimalsList
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimalsService {
    @GET("animals")
    suspend fun getAnimals(): List<AnimalsList>

    @GET("enviroment")
    suspend fun getAnimalById(@Path("id") id: String): AnimalsList)
}