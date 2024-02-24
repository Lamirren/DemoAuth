package com.example.demoauth.controller

import android.util.Patterns
import com.example.demoauth.model.UserModel
import com.example.demoauth.view.RegisterActivity

class RegisterController(private val view: RegisterActivity, private val userModel: UserModel) {
    fun register(username: String, email: String, password: String, confirmPassword: String) {
        val validationResult = validateInput(username, email, password, confirmPassword)
        if (validationResult.isValid) {
            userModel.register(email, password) { isSuccess, error ->
                if (isSuccess) {
                    view.onRegistrationSuccess()
                } else {
                    view.onRegistrationFailure(error ?: "Registration failed")
                }
            }
        } else {
            view.showInputError(validationResult.errorField, validationResult.errorMessage)
        }
    }

    private fun validateInput(username: String, email: String, password: String, confirmPassword: String): ValidationResult {
        if (username.isEmpty()) {
            return ValidationResult(false, "username", "This is a required field")
        }
        if (email.isEmpty()) {
            return ValidationResult(false, "email", "This is a required field")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(false, "email", "Check email format")
        }
        if (password.isEmpty()) {
            return ValidationResult(false, "password", "This is a required field")
        }
        if (password != confirmPassword) {
            return ValidationResult(false, "confirmPassword", "Passwords do not match")
        }
        return ValidationResult(true, "", "")
    }

    data class ValidationResult(val isValid: Boolean, val errorField: String, val errorMessage: String)
}
