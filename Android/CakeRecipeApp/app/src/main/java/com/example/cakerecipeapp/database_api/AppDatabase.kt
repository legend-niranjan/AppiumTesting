package com.example.cakerecipeapp.database_api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cakerecipeapp.dao.FavoriteRecipeDao
import com.example.cakerecipeapp.model.FavoriteRecipe

@Database(entities = [FavoriteRecipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}