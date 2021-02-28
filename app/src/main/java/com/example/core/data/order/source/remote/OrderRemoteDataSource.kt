package com.example.core.data.order.source.remote

import com.example.core.data.order.Order
import io.reactivex.Completable

interface OrderRemoteDataSource {
    fun orderUpload(email: String, item: Order): Completable
}