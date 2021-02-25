package com.example.core.ui.signin

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentSigninBinding
import com.example.core.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SigninFragment : BaseFragment<FragmentSigninBinding, SigninViewModel>(
    R.layout.fragment_signin
) {
    override val viewModel: SigninViewModel by viewModel()

    override fun init() {

        observing()
        textListener()
    }

    private fun observing() {
        with(viewModel) {
            validator.observe(viewLifecycleOwner, Observer {
                validatorText(it)
            })
            buttonState.observe(viewLifecycleOwner, Observer {
                binding.btnSignin.isEnabled = it
            })
        }
    }

    private fun textListener() {
        with(binding) {
            etEmail.addTextChangedListener { viewModel?.onNextEmail(it.toString()) }
            etPassword.addTextChangedListener { viewModel?.onNextPassword(it.toString()) }
            etPasswordConfirm.addTextChangedListener { viewModel?.onNextPasswordConfirm(it.toString()) }
            etName.addTextChangedListener { viewModel?.onNextName(it.toString()) }
            etCompany.addTextChangedListener { viewModel?.onNextCompany(it.toString()) }
            etPosition.addTextChangedListener { viewModel?.onNextPosition(it.toString()) }
            etCellPhone.addTextChangedListener { viewModel?.onNextCellPhone(it.toString()) }
        }
    }

    private fun validatorText(data: Pair<Int, Boolean>) = showAndHideText(data.first, data.second)

    private fun showAndHideText(type: Int, result: Boolean) {
        with(binding) {
            when (type) {
                0 -> etEmail.error = if (result)
                    null
                else
                    "이메일 입력"
                1 -> etPassword.error = if (result)
                    null
                else
                    "패스워드입력"
                2 -> etPasswordConfirm.error = if (result)
                    null
                else
                    "바르게 입력"
                3 -> etName.error = if (result)
                    null
                else
                    "이름 입력"
                4 -> etCompany.error = if (result)
                    null
                else
                    "바르게"
                5 -> etPosition.error = if (result)
                    null
                else
                    "직급"
                6 -> etCellPhone.error = if (result)
                    null
                else
                    "바르게 "
            }
        }
    }
}