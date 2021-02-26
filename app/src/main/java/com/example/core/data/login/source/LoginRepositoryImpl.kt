package com.example.core.data.login.source

import com.example.core.data.login.AuthLogin
import com.example.core.data.login.source.remote.LoginRemoteDataSource
import io.reactivex.Single

class LoginRepositoryImpl(private val remoteDataSource: LoginRemoteDataSource) : LoginRepository {
    override fun login(email: String, password: String): Single<AuthLogin> {
        return remoteDataSource.login(email, password)
    }
}