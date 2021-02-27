package com.example.core.modules

import com.example.core.ui.MainViewModel
import com.example.core.ui.login.LoginViewModel
import com.example.core.ui.main.MainFragmentViewModel
import com.example.core.ui.ownermain.OwnerMainViewModel
import com.example.core.ui.profile.ProfileViewModel
import com.example.core.ui.selfcall.SelfCallViewModel
import com.example.core.ui.signin.SigninViewModel
import com.example.core.ui.suggestion.SuggestionViewModel
import com.example.core.ui.tutorial.TutorialViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel() }
    single { LoginViewModel(get()) }
    single { TutorialViewModel(get()) }
    single { SigninViewModel(get()) }
    single { MainFragmentViewModel(get()) }
    single { OwnerMainViewModel() }
    single { ProfileViewModel(get()) }
    single { SelfCallViewModel(get()) }
    single { SuggestionViewModel(get()) }
}