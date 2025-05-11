package com.example.animalsapp.services

import com.example.animalsapp.models.AnimalsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalsService {
    @GET("animals")
    suspend fun getAnimals(): List<AnimalsItem>

    @GET("animals/{id}")
    suspend fun getAnimalById(@Path("id") id: String): AnimalsItem
}