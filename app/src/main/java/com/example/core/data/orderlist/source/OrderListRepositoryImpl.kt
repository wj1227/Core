package com.example.core.data.orderlist.source

import com.example.core.data.order.Order
import com.example.core.data.order.source.OrderRepository
import com.example.core.data.orderlist.OrderList
import com.example.core.data.orderlist.source.local.OrderListLocalDataSource
import com.example.core.data.orderlist.source.remote.OrderListRemoteDataSource
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import io.reactivex.Observable
import io.reactivex.Single

class OrderListRepositoryImpl(
    private val remoteDataSource: OrderListRemoteDataSource,
    private val localDataSource: OrderListLocalDataSource
) : OrderListRepository {
    override val email: String
        get() = localDataSource.email

    override fun getOrders(): Single<List<Order>> {
        return remoteDataSource.getOrders()
            .map { list ->
                list.filter { order ->
                    order.email == localDataSource.email
                }
            }
    }

    override fun getSuggestions(): Single<List<SuggestionItem>> {
        return remoteDataSource.getSuggestions()
            .map { list ->
                list.filter { order ->
                    order.email == localDataSource.email
                }
            }
    }

    override fun getSelfCalls(): Single<List<SelfCallItem>> {
        return remoteDataSource.getSelfCalls()
            .map { list ->
                list.filter { order ->
                    order.email == localDataSource.email
                }
            }
    }
}