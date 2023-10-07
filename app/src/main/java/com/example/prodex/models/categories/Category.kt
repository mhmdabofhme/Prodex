package com.example.prodex.models.categories

data class Category(
    val created_at: String,
    val deleted_at: String?,
    val id: Int,
    val image: String,
    val name: String,
    val status: Status
)