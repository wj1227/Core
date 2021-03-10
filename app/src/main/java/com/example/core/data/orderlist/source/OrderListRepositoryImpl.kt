package com.example.core.data.orderlist.source

import com.example.core.data.orderlist.source.local.OrderListLocalDataSource

class OrderListRepositoryImpl(
    private val localDataSource: OrderListLocalDataSource
) : OrderListRepository {
    override val email: String
        get() = localDataSource.email

}