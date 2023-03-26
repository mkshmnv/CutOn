package com.example.cuton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cuton.databinding.ActivityAuthorizationScreenBinding

class AuthorizationScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorizationScreenBinding

    private val mHandler = Handler(Looper.getMainLooper())
    private val mLauncher: Launcher = Launcher()
    override fun onStart() {
        super.onStart()
        mHandler.postDelayed(mLauncher, 2000.toLong())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonLogin.setOnClickListener {

//            val intent = Intent(this, HomeMenuActivity::class.java)
//            startActivity(intent)

            val userPhone = binding.editTextPhone.text.toString()
            val password = binding.editTextPassword.text.toString()

            // Перевірка коректності введених даних
            if (userPhone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show()
            } else if (userPhone == "admin" && password == "password") {
                // Якщо введені дані є вірними, переходимо до наступної активності
                val intent = Intent(this, AuthorizationScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onStop() {
        mHandler.removeCallbacks(mLauncher)
        super.onStop()
    }

    private fun launch() {
//        if (!isFinishing) {
//            startActivity(Intent(this@AuthorizationScreenActivity, HomeMenuActivity::class.java))
//            finish()
//        }
    }

    private inner class Launcher : Runnable {
        override fun run() {
            launch()
        }
    }
}

