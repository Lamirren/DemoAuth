package com.example.demoauth.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoauth.controller.LoginController
import com.example.demoauth.databinding.ActivityLoginBinding
import com.example.demoauth.model.UserModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginController: LoginController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        loginController = LoginController(this, UserModel(this))

        loginController.checkAutoLogin()

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            loginController.login(email, password)
        }

        binding.goToRegisterButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    fun onLoginSuccess() {
        Toast.makeText(this, "Authorization successful", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun onLoginFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    fun showAutoLogin() {
        Toast.makeText(this, "Automatic login", Toast.LENGTH_SHORT).show()
    }
}
