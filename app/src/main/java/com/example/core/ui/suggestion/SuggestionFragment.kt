package com.example.core.ui.suggestion

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseDialogFragment
import com.example.core.base.BaseFragment
import com.example.core.constants.LOADING
import com.example.core.databinding.FragmentSuggestionBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuggestionFragment : BaseFragment<FragmentSuggestionBinding, SuggestionViewModel>(
    R.layout.fragment_suggestion
) {
    override val viewModel: SuggestionViewModel by viewModel()
    private val loadingView by lazy { BaseDialogFragment(R.layout.fragment_loading) }

    override fun init() {
        with(viewModel) {
            state.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    SuggestionViewModel.SuggestionState.FAIL_VALIDATOR -> toast("내용을 입력해 주세요")
                    SuggestionViewModel.SuggestionState.SUCCESS_UPLOAD -> findNavController().popBackStack()
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
        binding.etText.addTextChangedListener { viewModel?.onNextText(it.toString()) }
    }

    private fun toast(message: String) = this.context?.showToast(message)
}