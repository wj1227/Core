package com.example.core.ui.order

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentOrderBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>(
    R.layout.fragment_order
) {
    override val viewModel: OrderViewModel by viewModel()

    override fun init() {
        with(viewModel) {
            errorMessage.observe(viewLifecycleOwner, Observer { errorMsg ->
                toast(errorMsg)
            })
            orderState.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    OrderViewModel.OrderState.SUCCESS_UPLOAD -> popBack()
                    OrderViewModel.OrderState.VALIDATOR -> toast("양식에 맞게 다시 입력해주세요")
                }
            })
        }

        initTextWatcher()
    }

    private fun initTextWatcher() {
        with(binding) {
            etEa.addTextChangedListener { viewModel?.onNextEa(it.toString()) }
            etText.addTextChangedListener { viewModel?.onNextText(it.toString()) }
        }
    }

    private fun toast(message: String) = this.context?.showToast(message)

    private fun popBack() {
        toast("주문접수 완료")
        findNavController().popBackStack()
    }
}