package com.example.core.ui.ownermain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.firestore.dataChanges
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType
import com.example.core.constants.*
import com.example.core.data.order.Order
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

interface OwnerMainViewModelType : ViewModelType<OwnerMainViewModelType.Input, OwnerMainViewModelType.Output> {
    interface Input {
        fun onOrderClick()
        fun onSuggestionClick()
        fun onSelfCallClick()
        fun onAllUserClick()
    }

    interface Output {
        val orderSize: LiveData<String>
        val suggestionSize: LiveData<String>
        val selfcallSize: LiveData<String>
        val allUserSize: LiveData<String>
    }
}

class OwnerMainViewModel(
    private val database: FirebaseFirestore
) : BaseViewModel(), OwnerMainViewModelType, OwnerMainViewModelType.Input, OwnerMainViewModelType.Output {
    override val input: OwnerMainViewModelType.Input
        get() = this

    override val outout: OwnerMainViewModelType.Output
        get() = this

    private val _allOrderListSubject: BehaviorSubject<ArrayList<Order>> = BehaviorSubject.create()
    private val _allSuggestionListSubject: BehaviorSubject<ArrayList<SuggestionItem>> = BehaviorSubject.create()
    private val _allSelfCallListSubject: BehaviorSubject<ArrayList<SelfCallItem>> = BehaviorSubject.create()
    private val _allUserListSubject: BehaviorSubject<ArrayList<SelfCallItem>> = BehaviorSubject.create()
    private val _clickSubject: Subject<OwnerMainState> = PublishSubject.create()

    private val _orderSize: MutableLiveData<String> = MutableLiveData()
    override val orderSize: LiveData<String>
        get() = _orderSize

    private val _suggestionSize: MutableLiveData<String> = MutableLiveData()
    override val suggestionSize: LiveData<String>
        get() = _suggestionSize

    private val _selfcallSize: MutableLiveData<String> = MutableLiveData()
    override val selfcallSize: LiveData<String>
        get() = _selfcallSize

    private val _allUserSize: MutableLiveData<String> = MutableLiveData()
    override val allUserSize: LiveData<String>
        get() = _allUserSize

    override fun onOrderClick() = _clickSubject.onNext(OwnerMainState.CLICK_ORDER)

    override fun onSuggestionClick() = _clickSubject.onNext(OwnerMainState.CLICK_SUGGESTION)

    override fun onSelfCallClick() = _clickSubject.onNext(OwnerMainState.CLICK_SELFCALL)

    override fun onAllUserClick() = _clickSubject.onNext(OwnerMainState.CLICK_ALLUSER)

    init {
        compositeDisposable.addAll(
            database.collection(ORDER)
                .dataChanges()
                .subscribe({
                    val orderList = it.value().toObjects(Order::class.java)
                    _allOrderListSubject.onNext(orderList as ArrayList<Order>)
                    _orderSize.value = "${orderList.size} 건"
                }, {
                    _orderSize.value = ""
                }),

            database.collection(SELF_CALL)
                .dataChanges()
                .subscribe({
                    val selfCallList = it.value().toObjects(SelfCallItem::class.java)
                    _allSelfCallListSubject.onNext(selfCallList as ArrayList<SelfCallItem>)
                    _selfcallSize.value = "${selfCallList.size} 건"
                }, {
                    _selfcallSize.value = ""
                }),

            database.collection(SUGGESTION)
                .dataChanges()
                .subscribe({
                    val suggestionList = it.value().toObjects(SuggestionItem::class.java)
                    _allSuggestionListSubject.onNext(suggestionList as ArrayList<SuggestionItem>)
                    _suggestionSize.value = "${suggestionList.size} 건"
                }, {
                    _suggestionSize.value = ""
                }),

            // 해결사항
            database.collection(USER_LIST)
                .dataChanges()
                .subscribe({
                    //val userList = it.value().toObjects(::class.java)
                    //_allSuggestionListSubject.onNext(suggestionList as ArrayList<SuggestionItem>)
                    //_suggestionSize.value = "${suggestionList.size} 건"
                }, {
                    _allUserSize.value = ""
                }),

            _clickSubject.observeOn(AndroidSchedulers.mainThread())
                .subscribe { setState(it) }
        )
    }

    private fun setState(state: OwnerMainState) {
        when (state) {
            OwnerMainState.CLICK_ORDER -> ""
            OwnerMainState.CLICK_SUGGESTION -> ""
            OwnerMainState.CLICK_SELFCALL -> ""
        }
    }

    enum class OwnerMainState {
        CLICK_ORDER,
        CLICK_SUGGESTION,
        CLICK_SELFCALL,
        CLICK_ALLUSER
    }
}