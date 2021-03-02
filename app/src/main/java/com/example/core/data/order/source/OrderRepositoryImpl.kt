package com.example.core.data.order.source

import com.example.core.data.order.Order
import com.example.core.data.order.source.local.OrderLocalDataSource
import com.example.core.data.order.source.remote.OrderRemoteDataSource
import io.reactivex.Completable

class OrderRepositoryImpl(
    private val localDataSource: OrderLocalDataSource,
    private val remoteDataSource: OrderRemoteDataSource
) : OrderRepository {
    override val name: String
        get() = localDataSource.name

    override val company: String
        get() = localDataSource.company

    override val cellPhone: String
        get() = localDataSource.cellPhone

    override val email: String
        get() = localDataSource.email

    override fun orderUpload(item: Order): Completable {
        return remoteDataSource.orderUpload(item)
    }
}