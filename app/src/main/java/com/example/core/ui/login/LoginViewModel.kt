package com.example.core.ui.login

import androidx.lifecycle.LiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.data.login.source.LoginRepository
import com.example.core.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface LoginViewModelType : ViewModelType<LoginViewModelType.Input, LoginViewModelType.Output> {
    interface Input {
        fun onLoginClick()
        fun onSigninClick()
        fun emailOnNext(email: String)
        fun passwordOnNext(password: String)
        fun autoLogin(autoLogin: Boolean)
    }
    interface Output {
        val loginState: LiveData<LoginViewModel.LoginState>
        val loading: LiveData<Boolean>
    }
}

class LoginViewModel(
    private val repository: LoginRepository
) : BaseViewModel(), LoginViewModelType, LoginViewModelType.Input, LoginViewModelType.Output {

    override val input: LoginViewModelType.Input
        get() = this
    override val outout: LoginViewModelType.Output
        get() = this

    private val _btnLoginSubject: Subject<Unit> = PublishSubject.create()
    private val _btnSigninSubject: Subject<Unit> = PublishSubject.create()
    private val _emailSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val _passwordSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")

    private val _loginState: SingleLiveEvent<LoginState> = SingleLiveEvent()
    override val loginState: LiveData<LoginState>
        get() = _loginState

    private val _loading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    override val loading: LiveData<Boolean>
        get() = _loading

    override fun onLoginClick() = _btnLoginSubject.onNext(Unit)
    override fun onSigninClick() = _btnSigninSubject.onNext(Unit)
    override fun emailOnNext(email: String) = _emailSubject.onNext(email)
    override fun passwordOnNext(password: String) = _passwordSubject.onNext(password)
    override fun autoLogin(autoLogin: Boolean) {
        repository.autoLogin = autoLogin
    }

    init {
        compositeDisposable.addAll(
            _btnLoginSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { loginValidator() },

            _btnSigninSubject.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { setState(LoginState.GO_SIGNIN) },

            _loadingSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_loading::setValue)
        )
    }


    private fun setState(state: LoginState) = _loginState.postValue(state)

    private fun loginValidator() {
        val email = _emailSubject.value
        val password = _passwordSubject.value

        when {
            email.isNullOrEmpty() -> setState(LoginState.EMAIL_EMPTY)
            password.isNullOrEmpty() -> setState(LoginState.PASSWORD_EMPTY)
            else -> login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        repository.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { hideLoading() }
            .subscribe({
                if (it.email == "aaa@aaa.com") {
                    setState(LoginState.GO_OWNER)
                } else {
                    setState(LoginState.GO_MAIN)
                }

                autoLogin(true)
            }, {
                _errorMessage.value = it.message
            }).addTo(compositeDisposable)
    }

    enum class LoginState {
        EMAIL_EMPTY,
        PASSWORD_EMPTY,
        GO_SIGNIN,
        GO_OWNER,
        GO_MAIN
    }
}