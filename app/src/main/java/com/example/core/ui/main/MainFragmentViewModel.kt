package com.example.core.ui.main

import androidx.lifecycle.LiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.data.main.source.MainRepository
import com.example.core.utils.SingleLiveEvent
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface MainFragmentViewModelType : ViewModelType<MainFragmentViewModelType.Input, MainFragmentViewModelType.Output> {
    interface Input {
        fun onReceiptClick()
        fun onSuggestionClick()
        fun onSelfCallClick()
        fun onMyOrderClcik()
        fun onMyProfileClick()
        fun onLogoutClick()
        fun onQuitClick()
    }

    interface Output {
        val mainState: LiveData<MainFragmentViewModel.MainState>
    }
}

class MainFragmentViewModel(
    private val repository: MainRepository
) : BaseViewModel(), MainFragmentViewModelType, MainFragmentViewModelType.Input, MainFragmentViewModelType.Output {
    override val input: MainFragmentViewModelType.Input
        get() = this
    override val outout: MainFragmentViewModelType.Output
        get() = this

    private val _receiptSubject: Subject<Unit> = PublishSubject.create()
    private val _suggestionSubject: Subject<Unit> = PublishSubject.create()
    private val _selfCallSubject: Subject<Unit> = PublishSubject.create()
    private val _myOrderSubject: Subject<Unit> = PublishSubject.create()
    private val _profileSubject: Subject<Unit> = PublishSubject.create()
    private val _logoutSubject: Subject<Unit> = PublishSubject.create()
    private val _quitSubject: Subject<Unit> = PublishSubject.create()

    override fun onReceiptClick() =_receiptSubject.onNext(Unit)
    override fun onSuggestionClick() = _suggestionSubject.onNext(Unit)
    override fun onSelfCallClick() = _selfCallSubject.onNext(Unit)
    override fun onMyOrderClcik() = _myOrderSubject.onNext(Unit)
    override fun onMyProfileClick() = _profileSubject.onNext(Unit)
    override fun onLogoutClick() = _logoutSubject.onNext(Unit)
    override fun onQuitClick() = _quitSubject.onNext(Unit)

    private val _mainState: SingleLiveEvent<MainState> = SingleLiveEvent()
    override val mainState: LiveData<MainState>
        get() = _mainState

    init {
        compositeDisposable.addAll(
            _logoutSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { repository.logout(); setState(MainState.LOGOUT_SUCCESS) },

            _profileSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(MainState.PROFILE_CHANGE) },

            _selfCallSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(MainState.SELF_CALL) },

            _suggestionSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(MainState.SUGGESTION) },

            _receiptSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(MainState.ORDER) },

            _myOrderSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(MainState.ORDER_LIST) }
        )
    }

    private fun setState(state: MainState) = _mainState.postValue(state)

    enum class MainState {
        LOGOUT_SUCCESS,
        PROFILE_CHANGE,
        SELF_CALL,
        SUGGESTION,
        ORDER,
        ORDER_LIST
    }
}