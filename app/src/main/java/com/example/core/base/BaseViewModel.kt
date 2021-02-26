package com.example.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable by lazy(::CompositeDisposable)
    protected val _loadingSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    protected val _errorMessage: BehaviorSubject<String> = BehaviorSubject.create()

    protected fun showLoading() = _loadingSubject.onNext(true)
    protected fun hideLoading() = _loadingSubject.onNext(false)
    protected fun errorMessage(message: String) = _errorMessage.onNext(message)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
        println("onClear")
    }
}