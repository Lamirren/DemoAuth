package com.example.demoauth.controller

import android.os.Handler
import android.os.Looper
import com.example.demoauth.model.UserModel
import com.example.demoauth.view.LoginActivity

class LoginController(private val view: LoginActivity, private val userModel: UserModel) {
    fun login(email: String, password: String) {
        userModel.login(email, password) { isSuccess, error ->
            if (isSuccess) {
                userModel.saveAutoLoginState(true)
                view.onLoginSuccess()
            } else {
                view.onLoginFailure(error ?: "Login failed")
            }
        }
    }

    fun checkAutoLogin() {
        if (userModel.isAutoLoginEnabled()) {
            view.showAutoLogin()
            // Delay and move
            Handler(Looper.getMainLooper()).postDelayed({
                view.onLoginSuccess()
            }, 2000)
        }
    }
}
