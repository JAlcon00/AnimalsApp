package com.example.animalsapp.models

data class EnviromentItem(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val facts: List<String>,
    val imageGallery: List<String>
)

