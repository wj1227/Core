package com.example.core.ui.orderlist

import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentOrderlistBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderListFragment : BaseFragment<FragmentOrderlistBinding, OrderListViewModel>(
    R.layout.fragment_orderlist
) {
    override val viewModel: OrderListViewModel by viewModel()

    override fun init() {

    }
}