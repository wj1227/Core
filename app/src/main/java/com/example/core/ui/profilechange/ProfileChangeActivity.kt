package com.example.core.ui.profilechange

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.databinding.ActivityProfileChangeBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileChangeActivity : BaseActivity<ActivityProfileChangeBinding, ProfileViewModel>(
    R.layout.activity_profile_change
) {
    override val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTextWatcher()
    }

    override fun initObserving() {
        super.initObserving()

        with(viewModel) {
            profileState.observe(this@ProfileChangeActivity, Observer { state ->
                when (state) {
                    ProfileViewModel.ProfileState.PROFILE_CHANGE -> "findNavController().popBackStack()"
                }
            })
//            loading.observe(viewLifecycleOwner, Observer { result ->
//                if (result) {
//                    loadingView.show(parentFragmentManager, LOADING)
//                } else {
//                    loadingView.dismissAllowingStateLoss()
//                }
//            })
            errorMessage.observe(this@ProfileChangeActivity, Observer { errorMsg ->
                this@ProfileChangeActivity.showToast(errorMsg)
            })
        }
    }

    private fun initTextWatcher() {
        with(binding) {
            etCompany.addTextChangedListener { viewModel?.onNextCompany(it.toString()) }
            etCellPhone.addTextChangedListener { viewModel?.onNextCellPhone(it.toString()) }
            etPosition.addTextChangedListener { viewModel?.onNextPosition(it.toString()) }
        }
    }
}