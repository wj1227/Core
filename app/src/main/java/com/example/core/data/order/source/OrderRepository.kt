package com.example.core.data.order.source

import com.example.core.data.order.Order
import io.reactivex.Completable

interface OrderRepository {
    val name: String
    val company: String
    val cellPhone: String
    val email: String

    fun orderUpload(item: Order): Completable
}