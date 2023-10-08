package com.example.prodex.network

import com.example.prodex.models.categories.CategoriesModel
import com.example.prodex.models.categories.Category
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/categories")
    suspend fun getCategories(): Response<CategoriesModel>

}