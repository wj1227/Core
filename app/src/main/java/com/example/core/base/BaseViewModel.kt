package com.example.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable by lazy(::CompositeDisposable)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}