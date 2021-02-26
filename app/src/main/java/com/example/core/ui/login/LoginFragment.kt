package com.example.core.ui.login

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseDialogFragment
import com.example.core.base.BaseFragment
import com.example.core.constants.LOADING
import com.example.core.databinding.FragmentLoginBinding
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login
) {

    override val viewModel: LoginViewModel by viewModel()
    private val loadingView by lazy { BaseDialogFragment(R.layout.fragment_loading) }

    override fun init() {

        observing()
        initTextWatcher()
    }

    private fun observing() {
        with(viewModel) {
            loginState.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    LoginViewModel.LoginState.EMAIL_EMPTY -> toast("이메일을 입력해주세요")
                    LoginViewModel.LoginState.PASSWORD_EMPTY -> toast("비밀번호를 입력해주세요")
                    LoginViewModel.LoginState.GO_SIGNIN -> goSignin()
                    LoginViewModel.LoginState.GO_MAIN -> goMain()
                    LoginViewModel.LoginState.GO_OWNER -> goOwnerMain()
                }
            })
            loading.observe(viewLifecycleOwner, Observer { result ->
                if (result) {
                    loadingView.show(parentFragmentManager, LOADING)
                } else {
                    loadingView.dismissAllowingStateLoss()
                }
            })
            errorMessage.observe(viewLifecycleOwner, Observer {
                toast(it)
            })
        }
    }

    private fun initTextWatcher() {
        with(binding) {
            etEmail.addTextChangedListener { viewModel?.emailOnNext(it.toString()) }
            etPassword.addTextChangedListener { viewModel?.passwordOnNext(it.toString()) }
        }
    }

    private fun toast(message: String) = this.context?.showToast(message)
    private fun goSignin() = findNavController().navigate(R.id.action_loginFragment_to_signinFragment)
    private fun goMain() = findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    private fun goOwnerMain() = findNavController().navigate(R.id.action_loginFragment_to_ownerMainFragment)

}