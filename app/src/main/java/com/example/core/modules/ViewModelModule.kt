package com.example.core.modules

import com.example.core.ui.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel() }
}