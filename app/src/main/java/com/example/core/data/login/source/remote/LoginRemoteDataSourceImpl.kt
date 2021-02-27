package com.example.core.data.login.source.remote

import com.example.core.data.login.AuthLogin
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single

class LoginRemoteDataSourceImpl(private val auth: FirebaseAuth) : LoginRemoteDataSource {
    override fun login(email: String, password: String): Single<AuthLogin> {
        return Single.create { emitter ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        currentUser?.let { firebaseUser ->
                            emitter.onSuccess(AuthLogin(firebaseUser.email ?: "이메일없음"))
                        } ?: emitter.onError(IllegalStateException("사용자가 없습니다"))
                    } else {
                        task.exception?.let {
                            emitter.onError(it)
                        } ?: emitter.onError(IllegalStateException("Firebase login exception error!"))
                    }
                }
        }
    }
}