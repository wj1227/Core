package com.example.core.modules

import com.example.core.ui.login.LoginViewModel
import com.example.core.ui.main.MainViewModel
import com.example.core.ui.order.OrderViewModel
import com.example.core.ui.orderlist.OrderListViewModel
import com.example.core.ui.ownermain.OwnerMainViewModel
import com.example.core.ui.profilechange.ProfileViewModel
import com.example.core.ui.selfcall.SelfCallViewModel
import com.example.core.ui.signin.SigninViewModel
import com.example.core.ui.splash.SplashViewModel
import com.example.core.ui.suggestion.SuggestionViewModel
import com.example.core.ui.tutorial.TutorialViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
    single { LoginViewModel(get()) }
    single { TutorialViewModel(get()) }
    single { SigninViewModel(get()) }
    single { OwnerMainViewModel(get()) }
    single { ProfileViewModel(get()) }
    single { SelfCallViewModel(get()) }
    single { SuggestionViewModel(get()) }
    single { OrderViewModel(get()) }
    single { OrderListViewModel(get(), get()) }
    single { SplashViewModel(get()) }
}