package com.example.ecommerceshoppingapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        signUpButton = findViewById(R.id.signUpButton)

        loginButton.setOnClickListener {
            val inputUsername = username.text.toString()
            val inputPassword = password.text.toString()

            if (inputUsername == "niranjan" && inputPassword == "niranjan") {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProductListingActivity::class.java))
            } else {
                Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}