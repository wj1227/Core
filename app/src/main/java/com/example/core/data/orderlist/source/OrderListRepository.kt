package com.example.core.data.orderlist.source

import com.example.core.data.order.Order
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import io.reactivex.Observable
import io.reactivex.Single

interface OrderListRepository {
    val email: String

    fun getOrders(): Single<List<Order>>
    fun getSuggestions(): Single<List<SuggestionItem>>
    fun getSelfCalls(): Single<List<SelfCallItem>>
}