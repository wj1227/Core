package com.example.core.ui.orderlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.firestore.dataChanges
import com.androidhuman.rxfirebase2.firestore.model.Value
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.constants.*
import com.example.core.data.order.Order
import com.example.core.data.orderlist.source.OrderListRepository
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import com.example.core.utils.SingleLiveEvent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxkotlin.addTo

interface OrderListViewModelType :
    ViewModelType<OrderListViewModelType.Input, OrderListViewModelType.Output> {
    interface Input {
        fun onOrderClick(order: List<Order>)
        fun onSuggestionClick(suggestion: List<SuggestionItem>)
        fun onSelfCallClick(selfCall: List<SelfCallItem>)
    }

    interface Output {
        val orderText: LiveData<String>
        val suggestionText: LiveData<String>
        val selfCallText: LiveData<String>
        val orders: LiveData<List<Order>>
        val suggestions: LiveData<List<SuggestionItem>>
        val selfCalls: LiveData<List<SelfCallItem>>
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


    private val _orderText: MutableLiveData<String> = MutableLiveData()
    override val orderText: LiveData<String>
        get() = _orderText

    private val _suggestionText: MutableLiveData<String> = MutableLiveData()
    override val suggestionText: LiveData<String>
        get() = _suggestionText

    private val _selfCallText: MutableLiveData<String> = MutableLiveData()
    override val selfCallText: LiveData<String>
        get() = _selfCallText

    private val _orders: SingleLiveEvent<List<Order>> = SingleLiveEvent()
    override val orders: LiveData<List<Order>>
        get() = _orders

    private val _suggestions: SingleLiveEvent<List<SuggestionItem>> = SingleLiveEvent()
    override val suggestions: LiveData<List<SuggestionItem>>
        get() = _suggestions

    private val _selfCalls: SingleLiveEvent<List<SelfCallItem>> = SingleLiveEvent()
    override val selfCalls: LiveData<List<SelfCallItem>>
        get() = _selfCalls

    override fun onOrderClick(order: List<Order>) {
        TODO("Not yet implemented")
    }

    override fun onSuggestionClick(suggestion: List<SuggestionItem>) {
        TODO("Not yet implemented")
    }

    override fun onSelfCallClick(selfCall: List<SelfCallItem>) {
        TODO("Not yet implemented")
    }

    init {
        database.collection(ORDER).whereEqualTo(EMAIL, repository.email)
            .dataChanges()
            .subscribe({
                val orderList = it.value().toObjects(Order::class.java)
                _orders.value = orderList
                _orderText.value = "${orderList.size} 건"
            }, {

            }).addTo(compositeDisposable)

        database.collection(SELF_CALL).whereEqualTo(EMAIL, repository.email)
            .dataChanges()
            .subscribe({
                val selfCallList = it.value().toObjects(SelfCallItem::class.java)
                _selfCalls.value = selfCallList
                _selfCallText.value = "${selfCallList.size} 건"
            }, {

            }).addTo(compositeDisposable)

        database.collection(SUGGESTION).whereEqualTo(EMAIL, repository.email)
            .dataChanges()
            .subscribe({
                val suggestionList = it.value().toObjects(SuggestionItem::class.java)
                _suggestions.value = suggestionList
                _suggestionText.value = "${suggestionList.size} 건"
            }, {

            }).addTo(compositeDisposable)
    }

}