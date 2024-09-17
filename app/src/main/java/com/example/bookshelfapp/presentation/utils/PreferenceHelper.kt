package com.example.bookshelfapp.presentation.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val LOGIN_EMAIL = "login_email"
        private const val LOGIN_PASSWORD = "login_password"
        private const val IS_LOGGED_IN = "is_logged_in"
    }

    fun saveLoginDetails(email: String, password: String) {
        val editor = preferences.edit()
        editor.putString(LOGIN_EMAIL, email)
        editor.putString(LOGIN_PASSWORD, password)
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.apply()
    }

    fun getLoginEmail(): String? = preferences.getString(LOGIN_EMAIL, null)

    fun getLoginPassword(): String? = preferences.getString(LOGIN_PASSWORD, null)

    fun isLoggedIn(): Boolean = preferences.getBoolean(IS_LOGGED_IN, false)

    fun clearLoginDetails() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}
