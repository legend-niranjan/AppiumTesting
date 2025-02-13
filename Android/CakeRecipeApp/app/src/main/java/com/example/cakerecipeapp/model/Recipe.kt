package com.example.cakerecipeapp.model

data class Recipe(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strIngredient1: String?,
    val strIngredient2: String?,
    // Add more ingredients as needed
)