package com.example.core.ui.orderlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.firestore.dataChanges
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.constants.EMAIL
import com.example.core.constants.ORDER
import com.example.core.constants.SELF_CALL
import com.example.core.constants.SUGGESTION
import com.example.core.data.order.Order
import com.example.core.data.orderlist.source.OrderListRepository
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import com.example.core.utils.SingleLiveEvent
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

interface OrderListViewModelType :
    ViewModelType<OrderListViewModelType.Input, OrderListViewModelType.Output> {
    interface Input {
        fun onOrderClick()
        fun onSuggestionClick()
        fun onSelfCallClick()
    }

    interface Output {
        val orderText: LiveData<String>
        val suggestionText: LiveData<String>
        val selfCallText: LiveData<String>
        val orders: LiveData<ArrayList<Order>>
        val suggestions: LiveData<ArrayList<SuggestionItem>>
        val selfCalls: LiveData<ArrayList<SelfCallItem>>
    }
}

class OrderListViewModel(
    private val repository: OrderListRepository,
    private val database: FirebaseFirestore
) : BaseViewModel(), OrderListViewModelType, OrderListViewModelType.Input, OrderListViewModelType.Output {
    private val TAG = javaClass.simpleName

    override val input: OrderListViewModelType.Input
        get() = this

    override val outout: OrderListViewModelType.Output
        get() = this

    private val _ordersListSubject: BehaviorSubject<ArrayList<Order>> = BehaviorSubject.create()
    private val _suggestionListSubject: BehaviorSubject<ArrayList<SuggestionItem>> = BehaviorSubject.create()
    private val _selfCallListSubject: BehaviorSubject<ArrayList<SelfCallItem>> = BehaviorSubject.create()
    private val _clickSubject: Subject<OrderListState> = PublishSubject.create()

    private val _orderText: MutableLiveData<String> = MutableLiveData()
    override val orderText: LiveData<String>
        get() = _orderText

    private val _suggestionText: MutableLiveData<String> = MutableLiveData()
    override val suggestionText: LiveData<String>
        get() = _suggestionText

    private val _selfCallText: MutableLiveData<String> = MutableLiveData()
    override val selfCallText: LiveData<String>
        get() = _selfCallText

    private val _orders: MutableLiveData<ArrayList<Order>> = MutableLiveData()
    override val orders: LiveData<ArrayList<Order>>
        get() = _orders

    private val _suggestions: MutableLiveData<ArrayList<SuggestionItem>> = MutableLiveData()
    override val suggestions: LiveData<ArrayList<SuggestionItem>>
        get() = _suggestions

    private val _selfCalls: MutableLiveData<ArrayList<SelfCallItem>> = MutableLiveData()
    override val selfCalls: LiveData<ArrayList<SelfCallItem>>
        get() = _selfCalls

    override fun onOrderClick() = _clickSubject.onNext(OrderListState.CLICK_ORDER)

    override fun onSuggestionClick() = _clickSubject.onNext(OrderListState.CLICK_SUGGESTION)

    override fun onSelfCallClick() = _clickSubject.onNext(OrderListState.CLICK_SELFCALL)

    init {
        compositeDisposable.addAll(
            database.collection(ORDER).whereEqualTo(EMAIL, repository.email)
                .dataChanges()
                .subscribe({
                    val orderList = it.value().toObjects(Order::class.java)
                    _ordersListSubject.onNext(orderList as ArrayList<Order>)
                    _orderText.value = "${orderList.size} 건"
                }, {

                }),

            database.collection(SELF_CALL).whereEqualTo(EMAIL, repository.email)
                .dataChanges()
                .subscribe({
                    val selfCallList = it.value().toObjects(SelfCallItem::class.java)
                    _selfCallListSubject.onNext(selfCallList as ArrayList<SelfCallItem>)
                    _selfCallText.value = "${selfCallList.size} 건"
                }, {

                }),

            database.collection(SUGGESTION).whereEqualTo(EMAIL, repository.email)
                .dataChanges()
                .subscribe({
                    val suggestionList = it.value().toObjects(SuggestionItem::class.java)
                    _suggestionListSubject.onNext(suggestionList as ArrayList<SuggestionItem>)
                    _suggestionText.value = "${suggestionList.size} 건"
                }, {

                }),

            _clickSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe { setList(it) }
        )
    }

    private fun setList(state: OrderListState) {
        when (state) {
            OrderListState.CLICK_ORDER -> _orders.value = _ordersListSubject.value
            OrderListState.CLICK_SUGGESTION -> _suggestions.value = _suggestionListSubject.value
            OrderListState.CLICK_SELFCALL -> _selfCalls.value = _selfCallListSubject.value
        }
    }

    enum class OrderListState {
        CLICK_ORDER,
        CLICK_SUGGESTION,
        CLICK_SELFCALL
    }

}