package com.example.core.data.orderlist.source

import com.example.core.data.orderlist.OrderList
import io.reactivex.Observable

interface OrderListRepository {
    fun getData(): Observable<OrderList>
}