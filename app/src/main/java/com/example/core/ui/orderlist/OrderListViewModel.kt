package com.example.core.ui.orderlist

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
import io.reactivex.schedulers.Schedulers
import org.koin.ext.scope

interface OrderListViewModelType :
    ViewModelType<OrderListViewModelType.Input, OrderListViewModelType.Output> {
    interface Input {

    }

    interface Output {
//        val allOrder: String
//        val laserWelding: String
//        val laserPiece: String
//        val algonWelding: String
//        val suggestion: String
//        val selfCall: String
    }
}

class OrderListViewModel(
    private val repository: OrderListRepository
) : BaseViewModel(), OrderListViewModelType, OrderListViewModelType.Input, OrderListViewModelType.Output {
    override val input: OrderListViewModelType.Input
        get() = this

    override val outout: OrderListViewModelType.Output
        get() = this

    init {
        repository.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { println("?: $it") }
            .doOnSubscribe { println("subs") }
            .doAfterTerminate { println("?? termi") }
            .doOnNext { println("zzz: $it") }



//        database.collection(ORDER)
//            .get()
//            .addOnSuccessListener { querySnapshot ->
////                for (data in querySnapshot) {
////                    val test =data.toObject(Order::class.java)
////                    println("test: $test")
////                }
//                val dd = querySnapshot.toObjects(Order::class.java).filter {
//                    it.tag == "레이저조각"
//                }
//
//                println("size: $dd")
//            }
//            .addOnFailureListener { println("주문접수 fail: $it") }
//
//        database.collection("건의사항").get()
//            .addOnSuccessListener {
//                println("123: ${it.size()}")
//            }
//            .addOnFailureListener { println("123 fail: ${it.message}") }


    }

}