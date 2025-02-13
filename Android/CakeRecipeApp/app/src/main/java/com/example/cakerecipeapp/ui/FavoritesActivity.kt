package com.example.cakerecipeapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cakerecipeapp.R
import com.example.cakerecipeapp.adapter.FavoriteRecipeAdapter
import com.example.cakerecipeapp.database_api.AppDatabase
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity() {
    private lateinit var adapter: FavoriteRecipeAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        database = AppDatabase.getDatabase(this)

        adapter = FavoriteRecipeAdapter { favoriteRecipe -> // Now it's FavoriteRecipe
            lifecycleScope.launch {
                database.favoriteRecipeDao().delete(favoriteRecipe) // Correct type
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadFavorites()
    }

    private fun loadFavorites() {
        database.favoriteRecipeDao().getAllFavorites().observe(this, { favorites ->
            adapter.submitList(favorites) // favorites should be a List<FavoriteRecipe>
        })
    }
}