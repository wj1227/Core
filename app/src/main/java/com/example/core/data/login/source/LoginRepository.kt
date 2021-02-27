package com.example.core.data.login.source

import com.example.core.data.login.AuthLogin
import io.reactivex.Single

interface LoginRepository {
    var autoLogin: Boolean

    fun login(email: String, password: String): Single<AuthLogin>
}