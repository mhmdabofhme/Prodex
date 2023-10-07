package com.example.prodex.models.product

import java.io.File


data class Product(
    val name: String,
    val link: String,
    val contact: String,
    val category: Int,
    val description: String,
    val additional_text: String?,
    val image: File?,
)
