package com.example.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable by lazy(::CompositeDisposable)
    protected val _loadingSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    //protected val _errorMessage: BehaviorSubject<String> = BehaviorSubject.create()

    protected val _errorMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    protected val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

//    protected val _loading: SingleLiveEvent<Boolean> = SingleLiveEvent()
//    val loading: LiveData<Boolean>
//        get() = _loading

    //protected fun showLoading() = _loadingSubject.onNext(true)
    //protected fun hideLoading() = _loadingSubject.onNext(false)
    //protected fun errorMessage(message: String) = _errorMessage.onNext(message)

    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}