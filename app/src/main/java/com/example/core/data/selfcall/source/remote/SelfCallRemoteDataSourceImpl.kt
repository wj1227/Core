package com.example.core.data.selfcall.source.remote

import com.example.core.constants.SELF_CALL
import com.example.core.data.selfcall.SelfCallItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable

class SelfCallRemoteDataSourceImpl(private val database: FirebaseFirestore) :
    SelfCallRemoteDataSource {
    override fun upload(item: SelfCallItem): Completable {
        return Completable.create { emitter ->
            database
                .collection(SELF_CALL)
                .add(item)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
        }
    }
}