package com.example.core.ui.orderlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.constants.ORDER
import com.example.core.constants.SELF
import com.example.core.constants.SUGGESTION
import com.example.core.data.order.Order
import com.example.core.data.orderlist.source.OrderListRepository
import com.example.core.data.suggestion.SuggestionItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.ext.scope

interface OrderListViewModelType :
    ViewModelType<OrderListViewModelType.Input, OrderListViewModelType.Output> {
    interface Input {
        fun onOrderClick()
        fun onSuggestionClick()
        fun onSelfCallClick()
    }

    interface Output {
        val orders: LiveData<String>
        val suggestions: LiveData<String>
        val selfCalls: LiveData<String>
    }
}

class OrderListViewModel(
    private val repository: OrderListRepository
) : BaseViewModel(), OrderListViewModelType, OrderListViewModelType.Input, OrderListViewModelType.Output {
    private val TAG = javaClass.simpleName

    override val input: OrderListViewModelType.Input
        get() = this

    override val outout: OrderListViewModelType.Output
        get() = this

    private val _orders: MutableLiveData<String> = MutableLiveData()
    override val orders: LiveData<String>
        get() = _orders

    private val _suggestions: MutableLiveData<String> = MutableLiveData()
    override val suggestions: LiveData<String>
        get() = _suggestions

    private val _selfCalls: MutableLiveData<String> = MutableLiveData()
    override val selfCalls: LiveData<String>
        get() = _selfCalls

    override fun onOrderClick() {
        TODO("Not yet implemented")
    }

    override fun onSuggestionClick() {
        TODO("Not yet implemented")
    }

    override fun onSelfCallClick() {
        TODO("Not yet implemented")
    }

    //todo 이쪽 코드 다시 신경써야함 1번 접근으로
    init {
        repository.getOrders()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "1 success ${it.size}")
                _orders.value = "${it.size} 건"
            }, {
                Log.d(TAG, "1 fail: ${it.message}")
            }).addTo(compositeDisposable)

        repository.getSelfCalls()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "2 success ${it.size}")
                _selfCalls.value = "${it.size} 건"
            }, {
                Log.d(TAG, "2 fail: ${it.message}")
            }).addTo(compositeDisposable)

        repository.getSuggestions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "3 success ${it.size}")
                _suggestions.value = "${it.size} 건"
            }, {
                Log.d(TAG, "3 fail: ${it.message}")
            }).addTo(compositeDisposable)
    }

}