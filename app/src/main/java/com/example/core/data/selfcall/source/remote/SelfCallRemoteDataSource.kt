package com.example.core.data.selfcall.source.remote

import com.example.core.data.selfcall.SelfCallItem
import io.reactivex.Completable

interface SelfCallRemoteDataSource {
    fun upload(item: SelfCallItem): Completable
}