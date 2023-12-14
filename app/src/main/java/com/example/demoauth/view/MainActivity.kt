package com.example.demoauth.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoauth.R
import com.example.demoauth.model.UserModel
import com.example.demoauth.controller.MainController
import com.example.demoauth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainController: MainController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mainController = MainController(this, UserModel(this))

        val user = mainController.getCurrentUser()
        if (user != null) {
            binding.greetingTextView.text = getString(R.string.greeting_message, user.email)
        } else {
            binding.greetingTextView.text = getString(R.string.greeting_unknown)
        }

        binding.logoutButton.setOnClickListener {
            mainController.logout()
        }
    }

    fun onLogout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
