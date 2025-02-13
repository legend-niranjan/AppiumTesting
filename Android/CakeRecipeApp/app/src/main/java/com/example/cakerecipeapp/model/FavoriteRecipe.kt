package com.example.cakerecipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipe(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val instructions: String
)