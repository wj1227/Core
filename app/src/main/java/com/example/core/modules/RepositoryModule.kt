package com.example.core.modules

import com.example.core.data.login.source.LoginRepository
import com.example.core.data.login.source.LoginRepositoryImpl
import com.example.core.data.main.source.MainRepository
import com.example.core.data.main.source.MainRepositoryImpl
import com.example.core.data.order.source.OrderRepository
import com.example.core.data.order.source.OrderRepositoryImpl
import com.example.core.data.orderlist.source.OrderListRepository
import com.example.core.data.orderlist.source.OrderListRepositoryImpl
import com.example.core.data.profile.source.ProfileRepository
import com.example.core.data.profile.source.ProfileRepositoryImpl
import com.example.core.data.selfcall.source.SelfCallRepository
import com.example.core.data.selfcall.source.SelfCallRepositoryImpl
import com.example.core.data.signin.source.SigninRepository
import com.example.core.data.signin.source.SigninRepositoryImpl
import com.example.core.data.suggestion.source.SuggestionRepository
import com.example.core.data.suggestion.source.SuggestionRepositoryImpl
import com.example.core.data.tutorial.source.TutorialRepository
import com.example.core.data.tutorial.source.TutorialRepositoryImpl
import org.koin.dsl.module
import kotlin.math.sign

val repositoryModule = module {
    single<TutorialRepository> {
        TutorialRepositoryImpl(
            get()
        )
    }

    single<SigninRepository> {
        SigninRepositoryImpl(
            get(), get()
        )
    }

    single<LoginRepository> {
        LoginRepositoryImpl(
            get(), get()
        )
    }

    single<MainRepository> {
        MainRepositoryImpl(
            get()
        )
    }

    single<ProfileRepository> {
        ProfileRepositoryImpl(
            get(), get()
        )
    }

    single<SelfCallRepository> {
        SelfCallRepositoryImpl(
            get(), get()
        )
    }

    single<SuggestionRepository> {
        SuggestionRepositoryImpl(
            get(), get()
        )
    }

    single<OrderRepository> {
        OrderRepositoryImpl(
            get(), get()
        )
    }

    single<OrderListRepository> {
        OrderListRepositoryImpl(
            get(), get()
        )
    }
}