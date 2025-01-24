package com.sopt.withsuhyeon.core.network

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun getIsFirstLogin(): Boolean {
        return sharedPreferences.getBoolean("isFirstLogin", true)
    }

    fun saveIsFirstLogin(isFirstLogin: Boolean) {
        sharedPreferences.edit().putBoolean("isFirstLogin", isFirstLogin).apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString("token", "").orEmpty()
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
    }
}
