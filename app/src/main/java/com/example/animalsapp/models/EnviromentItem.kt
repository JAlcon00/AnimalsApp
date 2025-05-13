package com.example.animalsapp.models

import com.google.gson.annotations.SerializedName

data class EnviromentItem(
    @SerializedName("_id")
    val id: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("description")
    val description: String = "",

    // Ajusta si la clave en JSON es distinta
    @SerializedName("image")
    val image: String = "",

    @SerializedName("facts")
    val facts: List<String> = emptyList(),

    @SerializedName("imageGallery")
    val imageGallery: List<String> = emptyList()
)

