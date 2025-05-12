// EnvironmentRepository.kt  → Repository
package com.example.animalsapp.repository

import com.example.animalsapp.models.EnviromentItem
import com.example.animalsapp.network.RetrofitInstance

object EnvironmentRepository {
    suspend fun getEnvironments(): List<EnviromentItem> =
        RetrofitInstance.envService.getEnvironments()

    suspend fun getEnvironmentById(id: String): EnviromentItem =
        RetrofitInstance.envService.getEnvironmentById(id)
}
