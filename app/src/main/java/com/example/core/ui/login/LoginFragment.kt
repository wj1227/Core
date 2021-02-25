package com.example.core.ui.login

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login
) {

    override val viewModel: LoginViewModel by viewModel()

    override fun init() {

        observing()
    }

    private fun observing() {
        with(viewModel) {
            loginState.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    LoginViewModel.LoginState.GO_LOGIN -> println("login!")
                    LoginViewModel.LoginState.GO_SIGNIN -> goSignin()
                }
            })
        }
    }

    private fun goSignin() = findNavController().navigate(R.id.action_loginFragment_to_signinFragment)

}