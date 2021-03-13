package com.example.core.ui.orderlist.order

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.constants.ORDER
import com.example.core.data.order.Order

class OrderListOrderActivity : AppCompatActivity() {

    private val orders by lazy {
        intent.getParcelableArrayListExtra<Order>(ORDER)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderListOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orderlist_order)

        initView()
        initAdapter()

        orders?.let {
            adapter.addItem(it)
        }
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun initAdapter() {
        adapter = OrderListOrderAdapter()
        recyclerView.adapter = adapter
    }


}