package com.example.cakerecipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakerecipeapp.R
import com.example.cakerecipeapp.model.FavoriteRecipe

class FavoriteRecipeAdapter(private val onDelete: (FavoriteRecipe) -> Unit) : ListAdapter<FavoriteRecipe, FavoriteRecipeAdapter.FavoriteRecipeViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_recipe, parent, false)
        return FavoriteRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
        holder.itemView.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            onDelete(recipe)
        }
    }

    class FavoriteRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: FavoriteRecipe) {
            itemView.findViewById<TextView>(R.id.recipeName).text = recipe.name
            Glide.with(itemView).load(recipe.image).into(itemView.findViewById(R.id.recipeImage))
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FavoriteRecipe>() {
            override fun areItemsTheSame(oldItem: FavoriteRecipe, newItem: FavoriteRecipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteRecipe, newItem: FavoriteRecipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}