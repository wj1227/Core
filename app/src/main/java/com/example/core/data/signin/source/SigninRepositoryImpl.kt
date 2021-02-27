package com.example.core.data.signin.source

import com.example.core.data.signin.SigninUser
import com.example.core.data.signin.source.local.SigninLocalDataSource
import com.example.core.data.signin.source.remote.SigninRemoteDataSource
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single

class SigninRepositoryImpl(
    private val remoteDataSource: SigninRemoteDataSource,
    private val localDataSource: SigninLocalDataSource
) : SigninRepository {
    override fun createUser(email: String, password: String, user: SigninUser): Single<FirebaseUser> {
        return remoteDataSource.createUser(email, password, user)
            .doOnSuccess { localDataSource.saveUser(user) }
    }
}