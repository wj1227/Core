package com.example.core.modules

import com.example.core.data.signin.source.SigninRepository
import com.example.core.data.signin.source.SigninRepositoryImpl
import com.example.core.data.tutorial.source.TutorialRepository
import com.example.core.data.tutorial.source.TutorialRepositoryImpl
import org.koin.dsl.module

val repository = module {
    single<TutorialRepository> {
        TutorialRepositoryImpl(
            get()
        )
    }

    single<SigninRepository> {
        SigninRepositoryImpl(
            get()
        )
    }
}