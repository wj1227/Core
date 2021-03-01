package com.example.core.data.orderlist.source

import com.example.core.data.order.source.OrderRepository
import com.example.core.data.orderlist.source.remote.OrderListRemoteDataSource

class OrderListRepositoryImpl(private val remoteDataSource: OrderListRemoteDataSource) : OrderListRepository {

}