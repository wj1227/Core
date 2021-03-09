package com.example.core.ui.suggestion

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.constants.LOADING
import com.example.core.databinding.ActivitySuggestionBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuggestionActivity : BaseActivity<ActivitySuggestionBinding, SuggestionViewModel>(
    R.layout.activity_suggestion
) {
    override val viewModel: SuggestionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTextWatcher()
    }

    override fun initObserving() {
        super.initObserving()

        with(viewModel) {
            state.observe(this@SuggestionActivity, Observer { state ->
                when (state) {
                    SuggestionViewModel.SuggestionState.FAIL_VALIDATOR -> this@SuggestionActivity.showToast("내용입력해라")
                    SuggestionViewModel.SuggestionState.SUCCESS_UPLOAD -> "findNavController().popBackStack()"
                }
            })
//            loading.observe(this@SuggestionActivity, Observer { result ->
//                if (result) {
//                    loadingView.show(parentFragmentManager, LOADING)
//                } else {
//                    loadingView.dismissAllowingStateLoss()
//                }
//            })
            errorMessage.observe(this@SuggestionActivity, Observer { errorMsg ->
                this@SuggestionActivity.showToast(errorMsg)
            })
        }
    }

    private fun initTextWatcher() {
        binding.etText.addTextChangedListener { viewModel?.onNextText(it.toString()) }
    }
}