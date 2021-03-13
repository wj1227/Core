package com.example.core.ui.selfcall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.selfcall.source.SelfCallRepository
import com.example.core.utils.SingleLiveEvent
import com.example.core.utils.ext.Quintuple
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function5
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface SelfCallViewModelType : ViewModelType<SelfCallViewModelType.Input, SelfCallViewModelType.Output> {
    interface Input {
        fun onUploadClick()
        fun onSelfClcik()
        fun onCallClick()
        fun onNextWork(work: String)
        fun onNextSalary(salary: String)
        fun onNextYear(year: String)
        fun onNextHope(hope: String)
    }

    interface Output {
        val tag: LiveData<String>
        val loading: LiveData<Boolean>
        val state: LiveData<SelfCallViewModel.SelfCallState>
    }
}

class SelfCallViewModel(
    private val repository: SelfCallRepository
) : BaseViewModel(), SelfCallViewModelType, SelfCallViewModelType.Input, SelfCallViewModelType.Output {
    override val input: SelfCallViewModelType.Input
        get() = this
    override val outout: SelfCallViewModelType.Output
        get() = this

    private val _btnUploadClick: Subject<Unit> = PublishSubject.create()
    private val _tagSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("구인")
    private val _workSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val _salarySubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val _yearSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val _hopeSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val _hotObservable = Observable.combineLatest(
        _tagSubject,
        _workSubject,
        _salarySubject,
        _yearSubject,
        _hopeSubject,
        Function5 { t1: String, t2: String, t3: String, t4: String, t5: String -> Quintuple(t1, t2, t3, t4, t5) }
    )
        .map { (t1, t2, t3, t4, t5) -> t1.isNotEmpty() && t2.isNotEmpty() && t3.isNotEmpty() && t4.isNotEmpty() }
        .replay(1).autoConnect()

    private val _tag: MutableLiveData<String> = MutableLiveData()
    override val tag: LiveData<String>
        get() = _tag

    private val _loading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    override val loading: LiveData<Boolean>
        get() = _loading

    private val _state: SingleLiveEvent<SelfCallState> = SingleLiveEvent()
    override val state: LiveData<SelfCallState>
        get() = _state

    override fun onUploadClick() = _btnUploadClick.onNext(Unit)
    override fun onSelfClcik() = _tagSubject.onNext("구직")
    override fun onCallClick() = _tagSubject.onNext("구인")
    override fun onNextWork(work: String) = _workSubject.onNext(work)
    override fun onNextSalary(salary: String) = _salarySubject.onNext(salary)
    override fun onNextYear(year: String) = _yearSubject.onNext(year)
    override fun onNextHope(hope: String) = _hopeSubject.onNext(hope)

    init {
        compositeDisposable.addAll(
            _btnUploadClick.throttleFirst(1, TimeUnit.SECONDS)
                .withLatestFrom(_hotObservable, BiFunction { _: Unit, x: Boolean -> x })
                .subscribe {
                    if (it) {
                        upload(createItem())
                    } else {
                        setState(SelfCallState.CANT_UPLOAD)
                    }
                },

            _tagSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_tag::setValue),

            _loadingSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_loading::setValue)
        )
    }

    private fun setState(state: SelfCallState) = _state.postValue(state)

    private fun upload(item: SelfCallItem) {
        repository.upload(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { hideLoading() }
            .subscribe({
                setState(SelfCallState.UPLOAD_SUCCESS)
            }, {
                _errorMessage.value = it.localizedMessage
            }).addTo(compositeDisposable)
    }

    private fun createItem() = SelfCallItem(
        email = repository.email,
        tag = _tagSubject.value!!,
        work = _workSubject.value!!,
        salary = _salarySubject.value!!,
        year = _yearSubject.value!!,
        hope = _hopeSubject.value!!,
        date = System.currentTimeMillis()
    )

    enum class SelfCallState {
        CANT_UPLOAD,
        UPLOAD_SUCCESS
    }
}