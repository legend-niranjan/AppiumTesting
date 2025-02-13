package com.example.ecommerceshoppingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Product(val name: String, val category: String, val price: Double)

class ProductListingActivity : AppCompatActivity() {

    private lateinit var productListView: ListView
    private lateinit var sortSpinner: Spinner
    private lateinit var filterSpinner: Spinner
    private lateinit var checkoutButton: Button

    private val allProducts = mutableListOf(
        Product("Laptop", "Electronics", 1000.0),
        Product("T-Shirt", "Clothing", 20.0),
        Product("Smartphone", "Electronics", 800.0),
        Product("Jeans", "Clothing", 50.0),
        Product("Book: Kotlin Programming", "Books", 30.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlisting)

        productListView = findViewById(R.id.productListView)
        sortSpinner = findViewById(R.id.sortSpinner)
        filterSpinner = findViewById(R.id.filterSpinner)
        checkoutButton = findViewById(R.id.checkoutButton)

        // Define sorting & filtering options in Kotlin (No strings.xml)
        val sortOptions = listOf("Low to High", "High to Low")
        val filterOptions = listOf("All", "Electronics", "Clothing", "Books")


        sortSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortOptions)
        filterSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, filterOptions)


        updateProductList()


        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> sortProductsByPrice(true)  // Low to High
                    1 -> sortProductsByPrice(false) // High to Low
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = filterOptions[position]
                filterProductsByCategory(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        checkoutButton.setOnClickListener {
            Toast.makeText(this, "Proceeding to Checkout", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@ProductListingActivity,CheckoutActivity::class.java))
        }
    }

    private fun updateProductList() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            allProducts.map { "${it.name} - ₹${it.price}" }
        )
        productListView.adapter = adapter
    }


    private fun sortProductsByPrice(ascending: Boolean) {
        allProducts.sortBy { it.price }
        if (!ascending) {
            allProducts.reverse()
        }
        updateProductList()
    }

    private fun filterProductsByCategory(category: String) {
        val filteredList = if (category == "All") {
            allProducts
        } else {
            allProducts.filter { it.category == category }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList.map { "${it.name} - ₹${it.price}" })
        productListView.adapter = adapter


        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No products found in this category.", Toast.LENGTH_SHORT).show()
        }
    }
}


