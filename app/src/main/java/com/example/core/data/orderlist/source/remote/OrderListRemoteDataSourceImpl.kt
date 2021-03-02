package com.example.core.data.orderlist.source.remote

import com.example.core.constants.ORDER
import com.example.core.constants.SELF_CALL
import com.example.core.constants.SUGGESTION
import com.example.core.data.order.Order
import com.example.core.data.orderlist.OrderList
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class OrderListRemoteDataSourceImpl(private val database: FirebaseFirestore) : OrderListRemoteDataSource {
    override fun getAll(): Observable<OrderList> {
        return Observable.create { emitter ->
            var order: List<Order>?
            var suggestion: List<SuggestionItem>?
            var selfCall: List<SelfCallItem>?

            database.collection(ORDER).get()
                .addOnSuccessListener { orders ->
                    println("1")
                    order = orders.toObjects(Order::class.java)

                    database.collection(SUGGESTION).get()
                        .addOnSuccessListener { suggestions ->
                            println("2")
                            suggestion = suggestions.toObjects(SuggestionItem::class.java)

                            database.collection(SELF_CALL).get()
                                .addOnSuccessListener { selfcalls ->
                                    println("3")
                                    selfCall = selfcalls.toObjects(SelfCallItem::class.java)
                                    emitter.onNext(OrderList(order, suggestion, selfCall))
                                }
                                .addOnFailureListener {
                                    println("333")
                                    emitter.onError(it)
                                }
                        }
                        .addOnFailureListener {
                            println("2222")
                            emitter.onError(it)
                        }
                }
                .addOnFailureListener {
                    println("11111")
                    emitter.onError(it)
                }

        }
    }


}