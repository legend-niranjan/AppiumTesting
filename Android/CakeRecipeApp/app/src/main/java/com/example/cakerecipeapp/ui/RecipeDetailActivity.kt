package com.example.cakerecipeapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.cakerecipeapp.R
import com.example.cakerecipeapp.database_api.AppDatabase
import com.example.cakerecipeapp.database_api.RetrofitClient
import com.example.cakerecipeapp.model.FavoriteRecipe
import com.example.cakerecipeapp.model.Recipe
import kotlinx.coroutines.launch

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private var currentRecipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        database = AppDatabase.getDatabase(this)

        val recipeId = intent.getStringExtra("recipeId")

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getCakeRecipes("cake")
                if (response.isSuccessful) {
                    val recipe = response.body()?.get("meals")?.find { it.idMeal == recipeId }
                    recipe?.let {
                        currentRecipe = it
                        findViewById<TextView>(R.id.recipeName).text = it.strMeal
                        findViewById<TextView>(R.id.recipeInstructions).text = it.strInstructions
                        Glide.with(this@RecipeDetailActivity).load(it.strMealThumb)
                            .into(findViewById(R.id.recipeImage))

                        findViewById<Button>(R.id.saveToFavorites).setOnClickListener {
                            saveToFavorites(currentRecipe)
                            startActivity(Intent(this@RecipeDetailActivity, FavoritesActivity::class.java))
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Handle back button click
        return true
    }


    private fun saveToFavorites(recipe: Recipe?) {
        if (recipe != null) {
            lifecycleScope.launch {
                database.favoriteRecipeDao().insert(
                    FavoriteRecipe(
                        recipe.idMeal,
                        recipe.strMeal,
                        recipe.strMealThumb,
                        recipe.strInstructions
                    )
                )
                Toast.makeText(this@RecipeDetailActivity, "Saved to Favorites", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this@RecipeDetailActivity, "Recipe not found", Toast.LENGTH_SHORT).show()

        }
    }
}