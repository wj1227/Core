package com.example.core.ui.orderlist.order

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.core.constants.ORDER
import com.example.core.data.order.Order

class OrderListOrderActivity : AppCompatActivity() {

    private val orders by lazy {
        intent.getParcelableArrayListExtra<Order>(ORDER)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("ok: $orders")
    }


}