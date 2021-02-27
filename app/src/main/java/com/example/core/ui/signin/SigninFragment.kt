package com.example.core.ui.signin

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseDialogFragment
import com.example.core.base.BaseFragment
import com.example.core.constants.LOADING
import com.example.core.databinding.FragmentSigninBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SigninFragment : BaseFragment<FragmentSigninBinding, SigninViewModel>(
    R.layout.fragment_signin
) {
    override val viewModel: SigninViewModel by viewModel()
    private val loadingView by lazy { BaseDialogFragment(R.layout.fragment_loading) }

    override fun init() {

        observing()
        textListener()
    }

    private fun observing() {
        with(viewModel) {
            validator.observe(viewLifecycleOwner, Observer {
                validatorText(it)
            })
            buttonState.observe(this@SigninFragment, Observer {
                binding.btnSignin.isEnabled = it
            })
            signinState.observe(this@SigninFragment, Observer {
                when (it) {
                    SigninViewModel.SigninState.SUCCESS -> findNavController().navigate(R.id.action_signinFragment_to_mainFragment)
                }
            })
            loading.observe(this@SigninFragment, Observer { result ->
                if (result) {
                    loadingView.show(parentFragmentManager, LOADING)
                } else {
                    loadingView.dismissAllowingStateLoss()
                }
            })
            errorMessage.observe(this@SigninFragment, Observer {
                this@SigninFragment.context?.showToast(it)
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
                    "이메일을 바르게 입력해주세요"
                1 -> etPassword.error = if (result)
                    null
                else
                    "6자리 이상 입력해주세요"
                2 -> etPasswordConfirm.error = if (result)
                    null
                else
                    "비밀번호가 일치하지 않습니다"
                3 -> etName.error = if (result)
                    null
                else
                    "이름을 바르게 입력해주세요"
                4 -> etCompany.error = if (result)
                    null
                else
                    "회사명을 바르게 입력해주세요"
                5 -> etPosition.error = if (result)
                    null
                else
                    "직급을 바르게 입력해주세요"
                6 -> etCellPhone.error = if (result)
                    null
                else
                    "핸드폰번호 자리수가 맞지 않습니다"
            }
        }
    }

}