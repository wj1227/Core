package com.example.core.ui.order

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.constants.LOADING
import com.example.core.databinding.ActivityOrderBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderActivity : BaseActivity<ActivityOrderBinding, OrderViewModel>(
    R.layout.activity_order
) {
    override val viewModel: OrderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTextWatcher()
    }

    override fun initObserving() {
        super.initObserving()

        with(viewModel) {
            errorMessage.observe(this@OrderActivity, Observer { errorMsg ->
                this@OrderActivity.showToast(errorMsg)
            })
            orderState.observe(this@OrderActivity, Observer { state ->
                when (state) {
                    OrderViewModel.OrderState.SUCCESS_UPLOAD -> "popBack()"
                    OrderViewModel.OrderState.VALIDATOR -> this@OrderActivity.showToast("양식에 맞게 입력")
                }
            })
//            loading.observe(this@OrderActivity, Observer { result ->
//                if (result) {
//                    loadingView.show(supportFragmentManager, LOADING)
//                } else {
//                    loadingView.dismissAllowingStateLoss()
//                }
//            })
        }
    }

    private fun initTextWatcher() {
        with(binding) {
            etEa.addTextChangedListener { viewModel?.onNextEa(it.toString()) }
            etText.addTextChangedListener { viewModel?.onNextText(it.toString()) }
        }
    }
}