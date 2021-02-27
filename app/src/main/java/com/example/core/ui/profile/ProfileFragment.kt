package com.example.core.ui.profile

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseDialogFragment
import com.example.core.base.BaseFragment
import com.example.core.constants.LOADING
import com.example.core.databinding.FragmentProfileBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(
    R.layout.fragment_profile
) {
    override val viewModel: ProfileViewModel by viewModel()
    private val loadingView by lazy { BaseDialogFragment(R.layout.fragment_loading) }

    override fun init() {
        with(viewModel) {
            profileState.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    ProfileViewModel.ProfileState.PROFILE_CHANGE -> findNavController().popBackStack()
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
                this@ProfileFragment.context?.showToast(errorMsg)
            })
        }

        initTextWatcher()
    }

    private fun initTextWatcher() {
        with(binding) {
            etCompany.addTextChangedListener { viewModel?.onNextCompany(it.toString()) }
            etCellPhone.addTextChangedListener { viewModel?.onNextCellPhone(it.toString()) }
            etPosition.addTextChangedListener { viewModel?.onNextPosition(it.toString()) }
        }
    }
}