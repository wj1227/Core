package com.example.core.data.profile.source.remote

import com.example.core.constants.USER_LIST
import com.example.core.data.profile.ProfileUser
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable

class ProfileRemoteDataSourceImpl(private val database: FirebaseFirestore) : ProfileRemoteDataSource {
    override fun updateUser(user: ProfileUser): Completable {
        return Completable.create { emitter ->
            database.collection(USER_LIST)
                .document(user.email)
                .set(user)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
        }
    }
}