package com.example.demoauth.model

import android.content.Context
import com.google.firebase.auth.FirebaseAuth

class UserModel(private val context: Context) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, null)
            } else {
                callback(false, task.exception?.message)
            }
        }
    }

    fun saveAutoLoginState(isEnabled: Boolean) {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("AUTO_LOGIN", isEnabled).apply()
    }

    fun isAutoLoginEnabled(): Boolean {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("AUTO_LOGIN", false) && auth.currentUser != null
    }

    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.signOut()
                callback(true, null)
            } else {
                callback(false, task.exception?.message)
            }
        }
    }

    fun getCurrentUser() = auth.currentUser

    fun logout() {
        auth.signOut()
    }
}
