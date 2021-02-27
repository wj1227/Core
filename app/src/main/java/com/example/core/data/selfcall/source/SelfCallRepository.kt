package com.example.core.data.selfcall.source

import com.example.core.data.selfcall.SelfCallItem
import io.reactivex.Completable

interface SelfCallRepository {
    val email: String
    val company: String

    fun upload(email: String, company: String, item: SelfCallItem): Completable
}