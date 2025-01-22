package com.sopt.withsuhyeon.core.network

import android.content.SharedPreferences
import com.sopt.withsuhyeon.BuildConfig.TOKEN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun getToken(): String {
        return sharedPreferences.getString("token", TOKEN) ?: TOKEN
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
    }
}