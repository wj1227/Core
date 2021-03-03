package com.example.core.data.orderlist.source.remote

import com.example.core.data.order.Order
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import io.reactivex.Single

interface OrderListRemoteDataSource {
    fun getOrders(): Single<List<Order>>
    fun getSuggestions(): Single<List<SuggestionItem>>
    fun getSelfCalls(): Single<List<SelfCallItem>>
}