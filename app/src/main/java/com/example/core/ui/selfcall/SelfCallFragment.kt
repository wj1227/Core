package com.example.core.ui.selfcall

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseDialogFragment
import com.example.core.base.BaseFragment
import com.example.core.constants.LOADING
import com.example.core.databinding.FragmentSelfcallBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelfCallFragment : BaseFragment<FragmentSelfcallBinding, SelfCallViewModel>(
    R.layout.fragment_selfcall
) {
    override val viewModel: SelfCallViewModel by viewModel()
    private val loadingView by lazy { BaseDialogFragment(R.layout.fragment_loading) }

    override fun init() {
        with(viewModel) {
            state.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    SelfCallViewModel.SelfCallState.CANT_UPLOAD -> this@SelfCallFragment.context?.showToast("양식을 바르게 입력해주세요")
                    SelfCallViewModel.SelfCallState.UPLOAD_SUCCESS -> {
                        toast("업로드 성공")
                        findNavController().popBackStack()
                    }
                }
            })
            loading.observe(viewLifecycleOwner, Observer { result ->
                if (result) {
                    loadingView.show(parentFragmentManager, LOADING)
                } else {
                    loadingView.dismissAllowingStateLoss()
                }
            })
            errorMessage.observe(viewLifecycleOwner, Observer { errorMsg ->
                toast(errorMsg)
            })
        }

        initTextWatcher()
    }

    private fun initTextWatcher() {
        with(binding) {
            etWork.addTextChangedListener { viewModel?.onNextWork(it.toString()) }
            etSalary.addTextChangedListener { viewModel?.onNextSalary(it.toString()) }
            etYear.addTextChangedListener { viewModel?.onNextYear(it.toString()) }
            etHope.addTextChangedListener { viewModel?.onNextHope(it.toString()) }
        }
    }

    private fun toast(message: String) = this.context?.showToast(message)

}