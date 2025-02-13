package com.example.sampleappium

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleappium.adpter.ProductAdapter
import com.example.sampleappium.databinding.ActivityProductBinding
import com.example.sampleappium.model.Product


class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val products = listOf(
            Product("Product 1", 10.99, "Detail for Product 1"),
            Product("Product 2", 19.99, "Detail for Product 2"),
            Product("Product 3", 29.99, "Detail for Product 3")
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(products){product: Product ->
             val intent = Intent(this, DetailActivity::class.java).apply {
                 putExtra("name", product.name)
                 putExtra("price", product.price)
                 putExtra("detail", product.detail)
             }
            startActivity(intent)

        }
        binding.recyclerView.adapter = adapter



    }
}