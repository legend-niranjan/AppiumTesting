package com.example.cakerecipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cakerecipeapp.model.Recipe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakerecipeapp.R

class RecipeAdapter(private val onClick: (Recipe) -> Unit) : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
        holder.itemView.setOnClickListener { onClick(recipe) }
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            itemView.findViewById<TextView>(R.id.recipeName).text = recipe.strMeal
            Glide.with(itemView).load(recipe.strMealThumb).into(itemView.findViewById(R.id.recipeImage))
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.idMeal == newItem.idMeal
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}