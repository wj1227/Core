package com.example.core.ui.orderlist

import android.os.Bundle
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
    }
}