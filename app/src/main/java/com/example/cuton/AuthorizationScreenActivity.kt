package com.example.cuton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AuthorizationScreenActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization_screen)

        editTextUsername = findViewById(R.id.editTextPhone)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            // Перевірка коректності введених даних
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show()
            } else if (username == "admin" && password == "password") {
                // Якщо введені дані є вірними, переходимо до наступної активності
                val intent = Intent(this, AuthorizationScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
