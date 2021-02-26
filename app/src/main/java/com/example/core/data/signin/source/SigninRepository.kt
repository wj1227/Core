package com.example.core.data.signin.source

import com.example.core.data.signin.SigninUser
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single

interface SigninRepository {
    fun createUser(email: String, password: String, user: SigninUser): Single<FirebaseUser>
}