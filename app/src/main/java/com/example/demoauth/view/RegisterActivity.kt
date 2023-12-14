package com.example.demoauth.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoauth.controller.RegisterController
import com.example.demoauth.databinding.ActivityRegisterBinding
import com.example.demoauth.model.UserModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerController: RegisterController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerController = RegisterController(this, UserModel(this))

        binding.registerButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()
            registerController.register(email, password, confirmPassword)
        }

        binding.goToLoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    fun showInputError(errorField: String, errorMessage: String) {
        when (errorField) {
            "email" -> binding.textInputLayoutEmail.error = errorMessage
            "password" -> binding.textInputLayoutPassword.error = errorMessage
            "confirmPassword" -> binding.textInputLayoutConfirmPassword.error = errorMessage
        }
    }

    fun onRegistrationSuccess() {
        Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun onRegistrationFailure(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()

        if (errorMessage.contains("email")) {
            binding.textInputLayoutEmail.error = errorMessage
        }
    }

}
