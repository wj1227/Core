package com.example.core.data.orderlist.source.remote

import com.example.core.data.orderlist.OrderList
import io.reactivex.Observable

interface OrderListRemoteDataSource {
    fun getAll(): Observable<OrderList>
}