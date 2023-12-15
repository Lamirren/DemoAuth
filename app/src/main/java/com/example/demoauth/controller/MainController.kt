package com.example.demoauth.controller

import com.example.demoauth.model.UserModel
import com.example.demoauth.view.MainActivity
import com.google.firebase.auth.FirebaseUser

class MainController(private val view: MainActivity, private val userModel: UserModel) {
    fun getCurrentUser(): FirebaseUser? {
        return userModel.getCurrentUser()
    }

    fun logout() {
        userModel.logout()
        view.onLogout()
    }
}
