package com.example.core.data.orderlist.source.remote

import com.example.core.constants.ORDER
import com.example.core.constants.SELF_CALL
import com.example.core.constants.SUGGESTION
import com.example.core.data.order.Order
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class OrderListRemoteDataSourceImpl(private val database: FirebaseFirestore) : OrderListRemoteDataSource {
    override fun getOrders(): Single<List<Order>> {
        return Single.create { emitter ->
            database.collection(ORDER).get()
                .addOnSuccessListener { orders ->
                    val currentItem = orders.toObjects(Order::class.java)
                    emitter.onSuccess(currentItem)
                }
                .addOnFailureListener { emitter.onError(it) }
        }
    }

    override fun getSuggestions(): Single<List<SuggestionItem>> {
        return Single.create { emitter ->
            database.collection(SUGGESTION).get()
                .addOnSuccessListener { suggestions ->
                    val currentItem = suggestions.toObjects(SuggestionItem::class.java)
                    emitter.onSuccess(currentItem)
                }
                .addOnFailureListener { emitter.onError(it) }
        }
    }

    override fun getSelfCalls(): Single<List<SelfCallItem>> {
        return Single.create { emitter ->
            database.collection(SELF_CALL).get()
                .addOnSuccessListener { selfCalls ->
                    val currentItem = selfCalls.toObjects(SelfCallItem::class.java)
                    emitter.onSuccess(currentItem)
                }
                .addOnFailureListener { emitter.onError(it) }
        }
    }

}