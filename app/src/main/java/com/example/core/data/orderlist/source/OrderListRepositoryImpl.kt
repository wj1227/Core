package com.example.core.data.orderlist.source

import com.example.core.data.order.source.OrderRepository
import com.example.core.data.orderlist.OrderList
import com.example.core.data.orderlist.source.remote.OrderListRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single

class OrderListRepositoryImpl(private val remoteDataSource: OrderListRemoteDataSource) : OrderListRepository {
    override fun getData(): Observable<OrderList> {
        return remoteDataSource.getAll()
    }
}