package com.example.core.data.signin.source.local

import com.example.core.data.signin.SigninUser

interface SigninLocalDataSource {
    fun saveUser(user: SigninUser)
}