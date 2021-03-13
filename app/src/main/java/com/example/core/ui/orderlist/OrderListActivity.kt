package com.example.core.ui.orderlist

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.databinding.ActivityOrderListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderListActivity : BaseActivity<ActivityOrderListBinding, OrderListViewModel>(
    R.layout.activity_order_list
) {
    override val viewModel: OrderListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initObserving() {
        super.initObserving()

        with(viewModel) {
            orders.observe(this@OrderListActivity, Observer {
                println("zzz: $it")
            })
            suggestions.observe(this@OrderListActivity, Observer {
                println("sug: $it")
            })
            selfCalls.observe(this@OrderListActivity, Observer {
                println("self: $it")
            })
        }
    }
}