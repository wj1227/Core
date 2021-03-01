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
import org.koin.ext.scope

interface OrderListViewModelType :
    ViewModelType<OrderListViewModelType.Input, OrderListViewModelType.Output> {
    interface Input {

    }

    interface Output {

    }
}

class OrderListViewModel(
    private val repository: OrderListRepository
) : BaseViewModel(), OrderListViewModelType, OrderListViewModelType.Input, OrderListViewModelType.Output {
    override val input: OrderListViewModelType.Input
        get() = this

    override val outout: OrderListViewModelType.Output
        get() = this

    val database = FirebaseFirestore.getInstance()

    //todo selfcall(구인구직), order(주문접수)에서 구조바꾸는게 좋을 것 같다..
    /**
     * 기존 구조 : 컬렉션(구인) -> 덕트먼트(회사) -> 컬렉션(이메일) -> 자동ID
     * 기존 구조 : 컬렉션(구직) -> 덕트먼트(회사) -> 컬렉션(이메일) -> 자동ID
     * 바꿀 구조 : 컬렉션(구인구직) -> 자동ID -> 컬렉션(구인 or 구직) -> 자동ID
     *
     * 기존 구조 : 컬렉션(주문접수) -> 덕트먼트(레이저,알곤) -> 컬렉션(이메일) -> 자동ID
     * 바꿀 구조 : 컬렉션(주문접수) -> 자동ID -> 컬렉션(태그) -> 자동ID
     */
    init {
//        val docRef = database.collection(SUGGESTION)
//        docRef.get()
//            .addOnSuccessListener { result ->
//                println("size: ${result.size()}")
//                for (document in result) {
//                    //println("ddd $document")
//                    //result.size()
//                }
//            }
//            .addOnFailureListener { exception ->
//                println("fail: $exception")
//            }

//        val docRef = database
//            .collection("주문접수")
//            .document("레이저용접")
//            .collection("test@test.com")
//        docRef.get()
//            .addOnSuccessListener { document ->
//                println("size: ${document.size()}")
//                if (document != null) {
//                    println("data: ${document}")
//                } else {
//                    println("else")
//                }
//            }
//            .addOnFailureListener { exception ->
//                println("fail: ${exception.message}")
//            }

       val dd = database.collection("테스트11")
           .document()
           .collection("테스트")

           dd.get()
           .addOnSuccessListener { document ->
               println("0size: ${document.size()}")
               if (document != null) {
                   println("0data: ${document}")
               } else {
                   println("else")
               }
           }
           .addOnFailureListener { exception ->
               println("fail: ${exception.message}")
           }



        val ee = database
            .collection("테스트11")
            .document()
            .collection("테스트1")

            ee.get()
            .addOnSuccessListener { document ->
                println("1size: ${document.size()}")
                if (document != null) {
                    println("1data: ${document}")
                } else {
                    println("else")
                }
            }
            .addOnFailureListener { exception ->
                println("fail: ${exception.message}")
            }

        val eeeee = database.collectionGroup("테스트1")
        eeeee.get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                println("size.... ${queryDocumentSnapshots.size()}")
                // [START_EXCLUDE]
                for (snap in queryDocumentSnapshots) {
                    println("snap: $snap")
                    //Log.d(TAG, "${snap.id} => ${snap.data}")
                }
                // [END_EXCLUDE]
            }


    }

}