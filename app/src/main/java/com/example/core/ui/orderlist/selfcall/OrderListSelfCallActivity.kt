package com.example.core.ui.orderlist.selfcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.constants.SELF_CALL
import com.example.core.data.selfcall.SelfCallItem

class OrderListSelfCallActivity : AppCompatActivity() {

    private val selfcall by lazy {
        intent.getParcelableArrayListExtra<SelfCallItem>(SELF_CALL)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderListSelfCallAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orderlist_order)

        initView()
        initAdapter()

        selfcall?.let {
            adapter.addItem(it)
        }
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun initAdapter() {
        adapter = OrderListSelfCallAdapter()
        recyclerView.adapter = adapter
    }

}
