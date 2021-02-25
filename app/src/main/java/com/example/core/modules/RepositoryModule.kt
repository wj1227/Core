package com.example.core.modules

import com.example.core.data.tutorial.TutorialRepository
import com.example.core.data.tutorial.TutorialRepositoryImpl
import org.koin.dsl.module

val repository = module {
    single<TutorialRepository> { TutorialRepositoryImpl(get()) }
}