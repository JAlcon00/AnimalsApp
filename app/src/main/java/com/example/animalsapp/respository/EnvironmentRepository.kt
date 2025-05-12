package com.example.animalsapp.repository

import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.network.RetrofitInstance

/**
 * Encapsula la fuente de datos de ambientes.
 */
object EnvironmentRepository {
    suspend fun getEnvironments(): List<EnviromentItem> =
        RetrofitInstance.envService.getEnviroments()

    suspend fun getEnvironmentById(id: String): EnviromentItem =
        RetrofitInstance.envService.getEnviromentById(id)
}
