package com.example.cuton

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuton.databinding.ActivityInitializeScreenBinding

//TODO fix launch icon

@Suppress("DEPRECATION")
class InitializeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitializeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInitializeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageViewLogo.alpha = 0f
        binding.imageViewLogo.animate().setDuration(1500).alpha(1f).withEndAction {
            val intent = Intent(this, AuthorizationScreenActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
