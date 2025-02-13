package com.example.cakerecipeapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cakerecipeapp.R
import com.example.cakerecipeapp.adapter.RecipeAdapter
import com.example.cakerecipeapp.database_api.AppDatabase
import com.example.cakerecipeapp.database_api.RetrofitClient
import kotlinx.coroutines.launch

class RecipeListActivity : AppCompatActivity() {
    private lateinit var adapter: RecipeAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        database = AppDatabase.getDatabase(this)

        adapter = RecipeAdapter { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("recipeId", recipe.idMeal)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchRecipes()
    }

    private fun fetchRecipes() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getCakeRecipes("cake")
                if (response.isSuccessful) {
                    val recipes = response.body()?.get("meals")
                    adapter.submitList(recipes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}