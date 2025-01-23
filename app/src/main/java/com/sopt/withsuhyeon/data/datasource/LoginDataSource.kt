package com.sopt.withsuhyeon.data.datasource

interface LoginDataSource {
    suspend fun postLoginPhoneNumberAuth(phoneNumber: String)
    suspend fun postLoginVerify(phoneNumber: String, verifyNumber: String)
    suspend fun postLogin(phoneNumber: String)
}