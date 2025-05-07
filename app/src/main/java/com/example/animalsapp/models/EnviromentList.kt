package com.example.animalsapp.models
import com.example.animalsapp.models.EnviromentItem
data class EnviromentList(
    val enviroment: List<EnviromentItem> = listOf(
            EnviromentItem(
                description = "",
                id = "",
                image = "",
                name = ""
            )
    )
)
