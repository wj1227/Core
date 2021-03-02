package com.example.core.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.data.order.Order
import com.example.core.data.order.source.OrderRepository
import com.example.core.utils.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

interface OrderViewModelType : ViewModelType<OrderViewModelType.Input, OrderViewModelType.Output> {
    interface Input {
        fun onLaserWeldingClick()
        fun onLaserPieceClcik()
        fun onAlgonWeldingClick()
        fun onUploadClick()
        fun onNextEa(ea: String)
        fun onNextText(text: String)
    }

    interface Output {
        val tag: LiveData<String>
        val orderState: LiveData<OrderViewModel.OrderState>
        val loading: LiveData<Boolean>
    }
}

class OrderViewModel(
    private val repository: OrderRepository
) : BaseViewModel(), OrderViewModelType, OrderViewModelType.Input, OrderViewModelType.Output {
    override val input: OrderViewModelType.Input
        get() = this

    override val outout: OrderViewModelType.Output
        get() = this

    private val _btnUploadSubject: Subject<Unit> = PublishSubject.create()
    private val _tagSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("레이저용접")
    private val _eaSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val _textSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    private val _hotObservable = Observable.combineLatest(
        _tagSubject,
        _eaSubject,
        _textSubject,
        Function3 { t1: String, t2: String, t3: String -> Triple(t1, t2, t3) }
    )
        .map { (t1, t2, t3) -> t1.isNotEmpty() && t2.isNotEmpty() }
        .replay(1).autoConnect()

    override fun onLaserWeldingClick() = _tagSubject.onNext("레이저용접")
    override fun onLaserPieceClcik() = _tagSubject.onNext("레이저조각")
    override fun onAlgonWeldingClick() = _tagSubject.onNext("알곤용접")
    override fun onNextEa(ea: String) = _eaSubject.onNext(ea)
    override fun onNextText(text: String) = _textSubject.onNext(text)
    override fun onUploadClick() = _btnUploadSubject.onNext(Unit)

    private val _tag: MutableLiveData<String> = MutableLiveData()
    override val tag: LiveData<String>
        get() = _tag

    private val _orderState: SingleLiveEvent<OrderState> = SingleLiveEvent()
    override val orderState: LiveData<OrderState>
        get() = _orderState

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    override val loading: LiveData<Boolean>
        get() = _loading

    init {
        compositeDisposable.addAll(
            _tagSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_tag::setValue),

            _btnUploadSubject.throttleFirst(1, TimeUnit.SECONDS)
                .withLatestFrom(_hotObservable, BiFunction { _: Unit, x: Boolean -> x })
                .subscribe { result ->
                    if (result) {
                        upload(createModel())
                    } else {
                        setState(OrderState.VALIDATOR)
                    }
                },

            _loadingSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(_loading::setValue)
        )
    }

    private fun upload(item: Order) {
        repository.orderUpload(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { hideLoading() }
            .subscribe({
                setState(OrderState.SUCCESS_UPLOAD)
            }, {
                _errorMessage.value = it.localizedMessage
            }).addTo(compositeDisposable)
    }

    private fun createModel() = Order(
        email = repository.email,
        tag = _tag.value!!,
        ea = _eaSubject.value!!.toInt(),
        text = _textSubject.value!!,
        name = repository.name,
        company = repository.company,
        cellPhone = repository.cellPhone,
        date = System.currentTimeMillis()
    )

    private fun setState(state: OrderState) = _orderState.postValue(state)

    enum class OrderState {
        SUCCESS_UPLOAD,
        VALIDATOR
    }
}