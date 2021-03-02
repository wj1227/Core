package com.example.core.data.order.source.remote

import com.example.core.constants.ORDER
import com.example.core.data.order.Order
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable

class OrderRemoteDataSourceImpl(private val database: FirebaseFirestore) : OrderRemoteDataSource {
    override fun orderUpload(item: Order): Completable {
        return Completable.create { emitter ->
            database.collection(ORDER)
                //..document(item.tag)
                //.collection(item.tag!!)
                //.set(item)
                .add(item)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { emitter.onError(it) }
        }
    }
}