package com.example.cakerecipeapp.database_api

import com.example.cakerecipeapp.model.Recipe
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbApi {
    @GET("api/json/v1/1/search.php")
    suspend fun getCakeRecipes(@Query("s") query: String): Response<Map<String, List<Recipe>>>
}

object RetrofitClient {
    private const val BASE_URL = "https://www.themealdb.com/"

    val instance: MealDbApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealDbApi::class.java)
    }
}