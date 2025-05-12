package com.example.animalsapp.repository

import com.example.animalsapp.models.AnimalsItem
import com.example.animalsapp.network.RetrofitInstance

/**
 * Encapsula la fuente de datos de animales.
 */
object AnimalsRepository {
    suspend fun getAnimals(): List<AnimalsItem> =
        RetrofitInstance.animalsService.getAnimals()

    suspend fun getAnimalById(id: String): AnimalsItem =
        RetrofitInstance.animalsService.getAnimalById(id)
}
