package com.example.core.data.orderlist.source.remote

import com.androidhuman.rxfirebase2.firestore.data
import com.example.core.constants.ORDER
import com.example.core.constants.SUGGESTION
import com.example.core.data.order.Order
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class OrderListRemoteDataSourceImpl(private val database: FirebaseFirestore) : OrderListRemoteDataSource {
//    override fun getSuggestion(): Single<List<SuggestionItem>> {
//
//    }
//
//    override fun getSelfCall(): Single<List<SelfCallItem>> {
//
//    }
//
//    override fun getOrder(): Single<List<Order>> {
//
//    }
//
//    private fun test(): Single<List<Order>> {
//        return Single.create { emitter ->
//            database.collection(ORDER)
//                .document("레이저용접")
//                .get()
//                .addOnSuccessListener {
//
//                }
//        }
//    }
}