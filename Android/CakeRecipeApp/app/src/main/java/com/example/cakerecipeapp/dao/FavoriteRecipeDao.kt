package com.example.cakerecipeapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cakerecipeapp.model.FavoriteRecipe


@Dao
interface FavoriteRecipeDao {
    @Insert
    suspend fun insert(recipe: FavoriteRecipe)

    @Delete
    suspend fun delete(recipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllFavorites(): LiveData<List<FavoriteRecipe>>
}