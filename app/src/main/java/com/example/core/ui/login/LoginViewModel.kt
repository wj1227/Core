package com.example.core.ui.login

import androidx.lifecycle.LiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.utils.SingleLiveEvent
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface LoginViewModelType : ViewModelType<LoginViewModelType.Input, LoginViewModelType.Output> {
    interface Input {
        fun onLoginClick()
        fun onSigninClick()
    }
    interface Output {
        val loginState: LiveData<LoginViewModel.LoginState>
    }
}

class LoginViewModel : BaseViewModel(), LoginViewModelType, LoginViewModelType.Input, LoginViewModelType.Output {

    override val input: LoginViewModelType.Input
        get() = this
    override val outout: LoginViewModelType.Output
        get() = this

    private val _btnLoginSubject: Subject<Unit> = PublishSubject.create()
    private val _btnSigninSubject: Subject<Unit> = PublishSubject.create()

    private val _loginState: SingleLiveEvent<LoginState> = SingleLiveEvent()
    override val loginState: LiveData<LoginState>
        get() = _loginState

    override fun onLoginClick() = _btnLoginSubject.onNext(Unit)
    override fun onSigninClick() = _btnSigninSubject.onNext(Unit)

    init {
        compositeDisposable.addAll(
            _btnLoginSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(LoginState.GO_LOGIN) },

            _btnSigninSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(LoginState.GO_SIGNIN) }
        )
    }

    private fun setState(state: LoginState) = _loginState.postValue(state)


    enum class LoginState {
        GO_SIGNIN,
        GO_LOGIN
    }
}