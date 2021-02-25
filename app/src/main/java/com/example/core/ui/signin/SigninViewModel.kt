package com.example.core.ui.signin

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.constants.PASSWORD_REGEX
import com.example.core.utils.ext.Septuple
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function7
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.regex.Pattern

interface SigninViewModelType : ViewModelType<SigninViewModelType.Input, SigninViewModelType.Output> {
    interface Input {
        fun onSigninClick()
    }

    interface Output {
        val validator: LiveData<Pair<Int, Boolean>>
        val buttonState: LiveData<Boolean>
    }
}

class SigninViewModel : BaseViewModel(), SigninViewModelType, SigninViewModelType.Input, SigninViewModelType.Output {
    override val input: SigninViewModelType.Input
        get() = this
    override val outout: SigninViewModelType.Output
        get() = this

    private val _btnSigninSubject: Subject<Unit> = PublishSubject.create()
    private val _emailSubject: BehaviorSubject<String> = BehaviorSubject.create()
    private val _passwordSubject: BehaviorSubject<String> = BehaviorSubject.create()
    private val _passwordConfrimSubject: BehaviorSubject<String> = BehaviorSubject.create()
    private val _nameSubject: BehaviorSubject<String> = BehaviorSubject.create()
    private val _companySubject: BehaviorSubject<String> = BehaviorSubject.create()
    private val _positionSubject: BehaviorSubject<String> = BehaviorSubject.create()
    private val _cellPhoneSubject: BehaviorSubject<String> = BehaviorSubject.create()
    private val _signinHotObservable = Observable.combineLatest(
        validatorEmail(),
        validatorPassword(),
        validatorPasswordConfirm(),
        validatorName(),
        validatorPosition(),
        validatorCompany(),
        validatorCellPhone(),
        Function7 { t1: Boolean, t2: Boolean, t3: Boolean, t4: Boolean, t5: Boolean, t6: Boolean, t7: Boolean -> Septuple(t1, t2, t3, t4, t5, t6, t7) }
    )
        .map { (t1, t2, t3, t4, t5, t6, t7) -> t1 && t2 && t3 && t4 && t5 && t6 && t7 }
        .replay(1).autoConnect()

    private val _validator: MutableLiveData<Pair<Int, Boolean>> = MutableLiveData()
    override val validator: LiveData<Pair<Int, Boolean>>
        get() = _validator

    private val _buttonState: MutableLiveData<Boolean> = MutableLiveData(false)
    override val buttonState: LiveData<Boolean>
        get() = _buttonState

    override fun onSigninClick() = _btnSigninSubject.onNext(Unit)

    init {
        compositeDisposable.addAll(
            _signinHotObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_buttonState::setValue),

            _btnSigninSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe { println("success!!!") }
        )
    }

    fun onNextEmail(email: String) = _emailSubject.onNext(email)
    fun onNextPassword(password: String) = _passwordSubject.onNext(password)
    fun onNextPasswordConfirm(passwordConfirm: String) = _passwordConfrimSubject.onNext(passwordConfirm)
    fun onNextName(name: String) = _nameSubject.onNext(name)
    fun onNextCompany(company: String) = _companySubject.onNext(company)
    fun onNextPosition(position: String) = _positionSubject.onNext(position)
    fun onNextCellPhone(cellPhone: String) = _cellPhoneSubject.onNext(cellPhone)

    private fun validatorEmail() =
        _emailSubject.map { email -> Patterns.EMAIL_ADDRESS.matcher(email).matches() }
            .doOnNext { _validator.value = Pair(0, it) }

    private fun validatorPassword() =
        _passwordSubject.map { p -> Pattern.matches(PASSWORD_REGEX, p) }
            .doOnNext { _validator.value = Pair(1, it) }

    private fun validatorPasswordConfirm() =
        Observable.combineLatest(
            _passwordSubject,
            _passwordConfrimSubject,
            BiFunction { t1: String, t2: String -> t1 to t2 }
        )
            .map { (t1, t2) -> t1 == t2 }
            .doOnNext { _validator.value = Pair(2, it) }

    private fun validatorName() =
        _nameSubject.map { name -> name.length >= 4 }
            .doOnNext { _validator.value = Pair(3, it) }

    private fun validatorCompany() =
        _companySubject.map { company -> company.length >= 2 }
            .doOnNext { _validator.value = Pair(4, it) }

    private fun validatorPosition() =
        _positionSubject.map { position -> position.length >= 2 }
            .doOnNext { _validator.value = Pair(5, it) }

    private fun validatorCellPhone() =
        _cellPhoneSubject.map { number -> number.length== 11 }
            .doOnNext { _validator.value = Pair(6, it) }


}