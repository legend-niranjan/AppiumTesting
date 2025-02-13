package com.example.ecommerceshoppingapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    private lateinit var payButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        payButton = findViewById(R.id.payButton)

        payButton.setOnClickListener {
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show()
            finish() // Close the checkout screen
        }
    }
}