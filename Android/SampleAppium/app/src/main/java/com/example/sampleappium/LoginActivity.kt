package com.example.sampleappium

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sampleappium.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.button.setOnClickListener {
            val inputUsername = binding.username.text.toString().trim()
            val inputPassword = binding.password.text.toString().trim()
            if(inputUsername=="Niranjan" && inputPassword=="1234"){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProductActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}