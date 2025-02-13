package com.example.sampleappium.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleappium.R
import com.example.sampleappium.model.Product

class ProductAdapter(private val product: List<Product>, private val onClick: (Product)->Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val pricetextView: TextView= itemView.findViewById(R.id.priceTextView)

        fun bind(product: Product) {
            nameTextView.text = product.name
            pricetextView.text = product.price.toString()
            itemView.setOnClickListener {
                onClick(product)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        holder.bind(product[position])
    }

    override fun getItemCount(): Int {
       return product.size
    }
}