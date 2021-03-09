package com.example.core.ui.selfcall

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.databinding.ActivitySelfCallBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelfCallActivity : BaseActivity<ActivitySelfCallBinding, SelfCallViewModel>(
    R.layout.activity_self_call
) {
    override val viewModel: SelfCallViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTextWatcher()
    }

    override fun initObserving() {
        super.initObserving()
        with(viewModel) {
            state.observe(this@SelfCallActivity, Observer { state ->
                when (state) {
                    SelfCallViewModel.SelfCallState.CANT_UPLOAD -> this@SelfCallActivity.showToast("양식 바르게")
                    SelfCallViewModel.SelfCallState.UPLOAD_SUCCESS -> {
                        this@SelfCallActivity.showToast("업로드성공")
                        //findNavController().popBackStack()
                    }
                }
            })
//            loading.observe(viewLifecycleOwner, Observer { result ->
//                if (result) {
//                    loadingView.show(parentFragmentManager, LOADING)
//                } else {
//                    loadingView.dismissAllowingStateLoss()
//                }
//            })
            errorMessage.observe(this@SelfCallActivity,Observer { errorMsg ->
                this@SelfCallActivity.showToast(errorMsg)
            })
        }
    }

    private fun initTextWatcher() {
        with(binding) {
            etWork.addTextChangedListener { viewModel?.onNextWork(it.toString()) }
            etSalary.addTextChangedListener { viewModel?.onNextSalary(it.toString()) }
            etYear.addTextChangedListener { viewModel?.onNextYear(it.toString()) }
            etHope.addTextChangedListener { viewModel?.onNextHope(it.toString()) }
        }
    }
}