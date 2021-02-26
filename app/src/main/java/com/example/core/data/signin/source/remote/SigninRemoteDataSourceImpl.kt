package com.example.core.data.signin.source.remote

import com.example.core.constants.USER_LIST
import com.example.core.data.signin.SigninUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class SigninRemoteDataSourceImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore
) : SigninRemoteDataSource {
    override fun createUser(email: String, password: String, user: SigninUser): Single<FirebaseUser> {
        return Single.create { emitter ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        currentUser?.let { firebaseUser ->
                            database.collection(USER_LIST)
                                .document(email)
                                .set(user)
                                .addOnSuccessListener { emitter.onSuccess(firebaseUser) }
                                .addOnFailureListener { emitter.onError(Throwable(IllegalArgumentException("Firebase database fail!"))) }
                        } ?: emitter.onError(Throwable(IllegalArgumentException("Firebase user error!")))
                    } else {
                        task.exception?.let {
                            emitter.onError(it)
                        } ?: emitter.onError(Throwable(IllegalArgumentException("Firebase exception error!")))
                    }
                }
        }
    }
}