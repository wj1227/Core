package com.example.core.data.login.source.remote

import com.example.core.data.login.AuthLogin
import io.reactivex.Single

interface LoginRemoteDataSource {
    fun login(email: String, password: String): Single<AuthLogin>
}